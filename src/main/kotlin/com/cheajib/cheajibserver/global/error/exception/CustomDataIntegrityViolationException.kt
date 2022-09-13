package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object CustomDataIntegrityViolationException : GlobalException(GlobalErrorCode.DATA_INTEGRITY_VIOLATION) {
    val EXCEPTION = CustomDataIntegrityViolationException
}
