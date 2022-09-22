package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class RestaurantDetailsResponse(
    val restaurantName: String,
    val address: String,
    val imageUrl: String?,
    val isVerify: Boolean
)
