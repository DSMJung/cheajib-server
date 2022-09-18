package com.cheajib.cheajibserver.domain.owner.exception

import com.cheajib.cheajibserver.domain.owner.exception.error.OwnerErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object OwnerNotFoundException : GlobalException(OwnerErrorCode.OWNER_NOT_FOUND) {
    val EXCEPTION = OwnerNotFoundException
}
