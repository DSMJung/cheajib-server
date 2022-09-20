package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class QueryRestaurantInfoResponse(
    val phoneNumber: String,
    val openHours: String,
    val latitude: Double,
    val longitude: Double
)
