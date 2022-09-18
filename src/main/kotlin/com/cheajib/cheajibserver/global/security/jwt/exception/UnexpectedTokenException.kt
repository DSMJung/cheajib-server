package com.cheajib.cheajibserver.global.security.jwt.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.security.jwt.exception.properties.JwtErrorCode

object UnexpectedTokenException : GlobalException(JwtErrorCode.UNEXPECTED_TOKEN) {
    val EXCEPTION = UnexpectedTokenException
}
