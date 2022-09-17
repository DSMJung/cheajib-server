package com.cheajib.cheajibserver.global.security.jwt

import com.cheajib.cheajibserver.global.security.jwt.exception.JwtValidateException
import com.cheajib.cheajibserver.global.security.jwt.properties.JwtProperty
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenResolver(
    private val jwtProperty: JwtProperty,
) {
    fun resolveToken(httpServletRequest: HttpServletRequest): String? =
        httpServletRequest.getHeader(jwtProperty.header)

    fun parseToken(token: String): String {
        if (token.startsWith(jwtProperty.prefix)) {
            return token.replace(jwtProperty.prefix, "")
        }
        throw JwtValidateException.EXCEPTION
    }
}
