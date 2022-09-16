package com.cheajib.cheajibserver.domain.user.exception

import com.cheajib.cheajibserver.domain.user.exception.properties.UserErrorProperty
import com.cheajib.cheajibserver.global.error.GlobalException

object UserNotFoundException : GlobalException(UserErrorProperty.USER_NOT_FOUND) {
    val EXCEPTION = UserNotFoundException
}
