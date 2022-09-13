package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomDataIntegrityViolationException : GlobalException(GlobalErrorCode.DATA_INTEGRITY_VIOLATION) {
    val EXCEPTION = CustomDataIntegrityViolationException
}