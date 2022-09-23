package com.cheajib.cheajibserver.domain.user.service

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import com.cheajib.cheajibserver.domain.user.domain.type.Sex
import com.cheajib.cheajibserver.global.security.jwt.JwtTokenProvider
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
import com.cheajib.cheajibserver.infrastructure.feign.client.NaverTokenClient
import com.cheajib.cheajibserver.infrastructure.feign.client.NaverUserInfoClient
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.NaverUserInfoElement
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.TokenResponse
import com.cheajib.cheajibserver.infrastructure.feign.properties.NaverFeignProperties
import org.springframework.stereotype.Service
import java.util.*

@Service
class NaverOauthService(
    private val naverTokenClient: NaverTokenClient,
    private val naverInfoFeignClient: NaverUserInfoClient,
    private val naverFeignProperties: NaverFeignProperties,
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {
    companion object {
        const val GRANT_TYPE = "authorization_code"
    }

    fun getCode(code: String): TokenResponse {
        val naverToken: String = "Bearer " + naverTokenClient.getCode(
            grantType = GRANT_TYPE,
            clientId = naverFeignProperties.clientId,
            clientSecret = naverFeignProperties.clientSecret,
            code = code,
            state = naverFeignProperties.state
        ).accessToken

        val userInfo: NaverUserInfoElement = naverInfoFeignClient.getUserInfo(naverToken).response

        var user: User? = userRepository.findByEmail(userInfo.email)

        if (user == null) {
            user = User(
                id = UUID(0, 0),
                email = userInfo.email,
                name = userInfo.name,
                level = Level.VEGAN,
                profile = DefaultImage.PROFILE_IMAGE,
                sex = Sex.U
            )
            userRepository.save(user)
        }

        return jwtTokenProvider.getToken(user.email)
    }
}
