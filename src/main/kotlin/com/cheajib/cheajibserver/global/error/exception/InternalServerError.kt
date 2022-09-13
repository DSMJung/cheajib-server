package com.cheajib.cheajibserver.global.error.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.propertys.GlobalErrorCode

object InternalServerError : GlobalException(GlobalErrorCode.INTERNAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerError
}
