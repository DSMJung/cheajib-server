package com.cheajib.cheajibserver.domain.restaurant.exception

import com.cheajib.cheajibserver.domain.restaurant.exception.properties.RestaurantErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object RestaurantNotFoundException : GlobalException(RestaurantErrorCode.RESTAURANT_NOT_FOUND) {
    val EXCEPTION = RestaurantNotFoundException
}
