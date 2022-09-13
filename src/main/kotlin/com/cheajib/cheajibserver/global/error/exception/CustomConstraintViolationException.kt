package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object CustomConstraintViolationException : GlobalException(GlobalErrorCode.CONSTRAINT_VIOLATION) {
    val EXCEPTION = CustomConstraintViolationException
}
