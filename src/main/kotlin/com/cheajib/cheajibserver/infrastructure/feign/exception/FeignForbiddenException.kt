package com.cheajib.cheajibserver.infrastructure.feign.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object FeignForbiddenException : GlobalException(GlobalErrorCode.FEIGN_FORBIDDEN) {
    val EXCEPTION = FeignForbiddenException
}
