package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.ErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomConstraintViolationException : GlobalException(ErrorCode.CONSTRAINT_VIOLATION){
    val EXCEPTION = CustomConstraintViolationException
}