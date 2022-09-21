package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import java.util.UUID
import javax.validation.constraints.NotNull

data class RestaurantMapResponse(
    val id: UUID,
    val name: String,
    val level: Level,
    val latitude: Double,
    val longitude: Double,
)
