package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantPreviewService(
    val restaurantFacade: RestaurantFacade,
) {

    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)

        return QueryRestaurantResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            starPoint = restaurantFacade.getStarPoint(restaurant),
            imageUrl = restaurantFacade.getImageUrl(restaurant),
            mainMenu = restaurantFacade.getMainMenu(restaurant),
            isVerify = restaurant.isVerify
        )
    }
}
