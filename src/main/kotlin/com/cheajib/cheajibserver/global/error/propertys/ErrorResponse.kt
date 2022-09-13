package com.cheajib.cheajibserver.global.error.propertys

import com.cheajib.cheajibserver.global.error.GlobalException

class ErrorResponse<T>(
    val errorStatus: Int,
    val errorMessage: String
) {
    companion object {
        fun of(exception: GlobalException): ErrorResponse<Unit> {
            return ErrorResponse(
                errorStatus = exception.errorStatus,
                errorMessage = exception.errorMessage
            )
        }
    }
}
