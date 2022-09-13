package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomMethodArgumentNotValidException : GlobalException(GlobalErrorCode.METHOD_ARGUMENT_NOT_VALID) {
    val EXCEPTION = CustomMethodArgumentNotValidException
}