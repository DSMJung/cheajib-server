package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryRestaurantInfoService(
    private val restaurantFacade: RestaurantFacade
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantInfoResponse {
        val restaurant: Restaurant = restaurantFacade.getRestaurantById(restaurantId)

        return QueryRestaurantInfoResponse(
            phoneNumber = restaurant.phoneNumber,
            openHours = restaurant.openHours,
            latitude = restaurant.latitude,
            longitude = restaurant.longitude
        )
    }
}
