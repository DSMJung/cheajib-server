package com.cheajib.cheajibserver.global.error

import com.cheajib.cheajibserver.global.error.propertys.ErrorProperty

open class GlobalException(
    private val errorProperty: ErrorProperty
) : RuntimeException() {

    val errorStatus: Int
        get() = errorProperty.errorStatus

    val errorMessage
        get() = errorProperty.errorMessage

    override fun fillInStackTrace(): Throwable {
        return this
    }
}
