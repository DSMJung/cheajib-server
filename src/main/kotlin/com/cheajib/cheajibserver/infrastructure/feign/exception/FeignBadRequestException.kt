package com.cheajib.cheajibserver.infrastructure.feign.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object FeignBadRequestException : GlobalException(GlobalErrorCode.FEIGN_BAD_REQUEST) {
    val EXCEPTION = FeignBadRequestException
}
