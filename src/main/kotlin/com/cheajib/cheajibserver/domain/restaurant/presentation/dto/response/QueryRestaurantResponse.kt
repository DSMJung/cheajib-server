package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class QueryRestaurantResponse(
    val restaurantName: String,
    val address: String,
    val starPoint: Int,
    val mainImageUrl: String,
    val mainMenuList: List<MainMenuListResponse>,
    val isVerify: Boolean
)
