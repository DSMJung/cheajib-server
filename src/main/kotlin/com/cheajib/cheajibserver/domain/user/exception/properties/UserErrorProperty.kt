package com.cheajib.cheajibserver.domain.user.exception.properties

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class UserErrorProperty(
    override val errorMessage: String,
    override val errorStatus: Int
) : ErrorProperty {

    USER_NOT_FOUND("User Not Found", 404)
}
