package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode

object CustomMethodArgumentNotValidException : GlobalException(GlobalErrorCode.METHOD_ARGUMENT_NOT_VALID) {
    val EXCEPTION = CustomMethodArgumentNotValidException
}
