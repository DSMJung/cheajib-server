package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantDetailsService(
    private val restaurantFacade: RestaurantFacade,
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): RestaurantDetailsResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)

        return RestaurantDetailsResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            imageUrl = restaurantFacade.getImageUrl(restaurant),
            isVerify = restaurant.isVerify
        )
    }
}
