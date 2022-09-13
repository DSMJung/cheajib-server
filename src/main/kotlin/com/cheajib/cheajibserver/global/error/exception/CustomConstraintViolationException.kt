package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomConstraintViolationException : GlobalException(GlobalErrorCode.CONSTRAINT_VIOLATION){
    val EXCEPTION = CustomConstraintViolationException
}