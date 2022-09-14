package com.cheajib.cheajibserver.domain.restaurant.exception.properties

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class RestaurantErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {
    RESTAURANT_NOT_FOUND(404, "Restaurant Not Found")
}
