package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.ErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomMethodArgumentNotValidException : GlobalException(ErrorCode.METHOD_ARGUMENT_NOT_VALID) {
    val EXCEPTION = CustomMethodArgumentNotValidException
}