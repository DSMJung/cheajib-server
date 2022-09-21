package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class QueryRestaurantResponse(
    val restaurantName: String,
    val address: String,
    val starPoint: Int,
    val imageUrl: String,
    val mainMenuList: String,
    val isVerify: Boolean
)
