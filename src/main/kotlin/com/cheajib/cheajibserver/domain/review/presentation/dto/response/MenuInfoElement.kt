package com.cheajib.cheajibserver.domain.review.presentation.dto.response

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import java.util.UUID

data class MenuInfoElement (
    val menuId: UUID,
    val level: Level
)
