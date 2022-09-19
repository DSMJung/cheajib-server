package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

data class QueryRestaurantReviewResponse(
    val averageStar: Double,
    val starCount: MutableList<Int>,
    val reviewList: List<QueryReviewResponse>
)
