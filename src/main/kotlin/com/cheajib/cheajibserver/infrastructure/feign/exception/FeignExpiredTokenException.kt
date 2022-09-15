package com.cheajib.cheajibserver.infrastructure.feign.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object FeignExpiredTokenException : GlobalException(GlobalErrorCode.FEIGN_EXPIRED_TOKEN) {
    val EXCEPTION = FeignExpiredTokenException
}