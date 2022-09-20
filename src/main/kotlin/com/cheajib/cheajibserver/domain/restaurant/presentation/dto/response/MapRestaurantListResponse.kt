package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import java.util.UUID

data class MapRestaurantListResponse(
    val id: UUID,
    val name: String,
    val level: Level
)
