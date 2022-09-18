package com.cheajib.cheajibserver.global.security.jwt.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.security.jwt.exception.properties.JwtErrorCode

object JwtValidateException : GlobalException(JwtErrorCode.JWT_VALIDATE_FAILED) {
    val EXCEPTION = JwtValidateException
}
