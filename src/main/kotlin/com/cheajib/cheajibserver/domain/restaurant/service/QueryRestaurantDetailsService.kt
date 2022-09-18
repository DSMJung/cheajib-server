package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryRestaurantDetailsService(
    private val restaurantFacade: RestaurantFacade,
    private val menuFacade: MenuFacade
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): RestaurantDetailsResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val menu = menuFacade.getMenuByRestaurant(restaurant)
        val menuImage = menu.menuImageUrl

        return RestaurantDetailsResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            imageUrl = menuImage,
            isVerify = restaurant.isVerify
        )
    }
}
