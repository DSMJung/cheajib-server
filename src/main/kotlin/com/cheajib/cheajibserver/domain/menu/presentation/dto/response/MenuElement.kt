package com.cheajib.cheajibserver.domain.menu.presentation.dto.response

import java.util.UUID

data class MenuElement(
    val menuId: UUID,
    val name: String,
    val description: String,
    val price: String,
    val menuImageUrl: String,
    val average: String,
    val menuCount: Int
)
