package com.cheajib.cheajibserver.infrastructure.aws.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object ImageNotFoundException : GlobalException(GlobalErrorCode.IMAGE_NOT_FOUND) {
    val EXCEPTION = ImageNotFoundException
}
