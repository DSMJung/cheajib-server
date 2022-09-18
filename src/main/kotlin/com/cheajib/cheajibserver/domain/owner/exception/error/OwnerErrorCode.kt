package com.cheajib.cheajibserver.domain.owner.exception.error

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class OwnerErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {
    OWNER_NOT_FOUND(404, "Owner Not Found")
}