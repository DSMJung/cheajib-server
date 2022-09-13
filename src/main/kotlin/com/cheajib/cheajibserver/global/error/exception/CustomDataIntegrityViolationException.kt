package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.ErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomDataIntegrityViolationException : GlobalException(ErrorCode.DATA_INTEGRITY_VIOLATION) {
    val EXCEPTION = CustomDataIntegrityViolationException
}