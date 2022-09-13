package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object InternalServerError : GlobalException(GlobalErrorCode.INTERNAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerError
}