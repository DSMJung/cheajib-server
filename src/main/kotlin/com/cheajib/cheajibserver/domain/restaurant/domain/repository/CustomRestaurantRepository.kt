package com.cheajib.cheajibserver.domain.restaurant.domain.repository

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo.ReviewVO

interface CustomRestaurantRepository {
    fun queryReview(restaurant: Restaurant): List<ReviewVO>?
}
