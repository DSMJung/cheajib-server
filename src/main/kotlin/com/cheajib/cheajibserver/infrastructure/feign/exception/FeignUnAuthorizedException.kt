package com.cheajib.cheajibserver.infrastructure.feign.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object FeignUnAuthorizedException : GlobalException(GlobalErrorCode.FEIGN_UN_AUTHORIZED) {
    val EXCEPTION = FeignUnAuthorizedException
}
