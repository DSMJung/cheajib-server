package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.ErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object InternalServerError : GlobalException(ErrorCode.INTERNAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerError
}