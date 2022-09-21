package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

import java.util.UUID

data class RestaurantListResponse(
    val id: UUID,
    val name: String,
    val address: String,
    val starPoint: Double,
    val mainMenu: String,
    val imageUrl: String,
    val isVerify: Boolean
)
