package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode

object CustomIllegalArgumentException : GlobalException(GlobalErrorCode.ILLEGAL_ARGUMENT) {
    val EXCEPTION = CustomIllegalArgumentException
}
