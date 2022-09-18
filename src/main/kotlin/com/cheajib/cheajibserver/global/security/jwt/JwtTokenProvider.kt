package com.cheajib.cheajibserver.global.security.jwt

import com.cheajib.cheajibserver.domain.user.domain.RefreshToken
import com.cheajib.cheajibserver.domain.user.domain.repository.RefreshTokenRepository
import com.cheajib.cheajibserver.global.security.auth.AuthDetailsService
import com.cheajib.cheajibserver.global.security.jwt.exception.ExpiredTokenException
import com.cheajib.cheajibserver.global.security.jwt.exception.JwtValidateException
import com.cheajib.cheajibserver.global.security.jwt.exception.SignatureTokenException
import com.cheajib.cheajibserver.global.security.jwt.exception.UnexpectedTokenException
import com.cheajib.cheajibserver.global.security.jwt.properties.JwtProperty
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.TokenResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date

@Component
class JwtTokenProvider(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperty: JwtProperty,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    companion object {
        private const val REFRESH_KEY = "refresh"
        private const val ACCESS_KEY = "access"
    }

    fun getToken(email: String): TokenResponse {
        val accessToken: String = generateToken(email, jwtProperty.accessExp, ACCESS_KEY)
//        val refreshToken: String = generateRefreshToken(email)

        return TokenResponse(accessToken = accessToken)
    }

    fun generateRefreshToken(email: String): String {
        val newRefreshToken: String = generateToken(email, jwtProperty.refreshExp, REFRESH_KEY)
        refreshTokenRepository.save(
            RefreshToken(
                email = (email),
                token = newRefreshToken
            )
        )
        return newRefreshToken
    }

    private fun generateToken(email: String, expiration: Long, type: String): String {
        return "Bearer " + Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .signWith(SignatureAlgorithm.HS512, jwtProperty.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .setHeaderParam("typ", type)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication? {
        return token?.let {
            val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))

            return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        }
    }

    private fun getTokenSubject(subject: String): String {
        return parseTokenBody(subject).subject
    }

    private fun parseTokenBody(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperty.secretKey)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw ExpiredTokenException.EXCEPTION
                is SignatureException -> throw SignatureTokenException.EXCEPTION
                is MalformedJwtException -> throw JwtValidateException.EXCEPTION
                else -> throw UnexpectedTokenException.EXCEPTION
            }
        }
    }

    fun getExpiredTime(): LocalDateTime? {
        return LocalDateTime.now().plusSeconds(jwtProperty.refreshExp)
    }
}
