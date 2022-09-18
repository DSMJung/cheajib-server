package com.cheajib.cheajibserver.global.security.jwt.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.security.jwt.exception.properties.JwtErrorCode

object SignatureTokenException : GlobalException(JwtErrorCode.JWT_SIGNATURE) {
    val EXCEPTION = SignatureTokenException
}
