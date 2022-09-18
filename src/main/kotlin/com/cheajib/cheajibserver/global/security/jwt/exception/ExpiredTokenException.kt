package com.cheajib.cheajibserver.global.security.jwt.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.security.jwt.exception.properties.JwtErrorCode

object ExpiredTokenException : GlobalException(JwtErrorCode.JWT_EXPIRED) {
    val EXCEPTION = ExpiredTokenException
}
