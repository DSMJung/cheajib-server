package com.cheajib.cheajibserver.domain.menu.exception

import com.cheajib.cheajibserver.domain.menu.exception.properties.MenuErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object MenuNotFoundException : GlobalException(MenuErrorCode.MENU_NOT_FOUND) {
    val EXCEPTION = MenuNotFoundException
}
