package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class QueryRestaurantResponse(
    val restaurantName: String,
    val address: String,
    val starPoint: Double,
    val imageUrl: String?,
    val mainMenu: String?,
    val isVerify: Boolean
)
