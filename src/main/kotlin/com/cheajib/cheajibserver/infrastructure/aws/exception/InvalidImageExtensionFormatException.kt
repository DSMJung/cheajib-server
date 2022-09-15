package com.cheajib.cheajibserver.infrastructure.aws.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object InvalidImageExtensionFormatException : GlobalException(GlobalErrorCode.INVALID_EXTENSION_FORMAT) {
    val EXCEPTION = InvalidImageExtensionFormatException
}