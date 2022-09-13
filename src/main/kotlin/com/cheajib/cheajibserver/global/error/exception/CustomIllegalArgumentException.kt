package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.ErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object CustomIllegalArgumentException: GlobalException(ErrorCode.ILLEGAL_ARGUMENT) {
    val EXCEPTION = CustomIllegalArgumentException
}