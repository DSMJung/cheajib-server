package com.cheajib.cheajibserver.domain.menu.exception.properties

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class MenuErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {
    MENU_NOT_FOUND(404, "Menu Not Found")
}
