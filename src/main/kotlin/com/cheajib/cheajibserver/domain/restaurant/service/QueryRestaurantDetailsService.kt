package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantDetailsService(
    private val restaurantFacade: RestaurantFacade,
    private val menuFacade: MenuFacade
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): RestaurantDetailsResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val menu = menuFacade.getMenuByRestaurant(restaurant)
        var menuImage = restaurant.imageUrl

        if (restaurant.imageUrl == DefaultImage.RESTAURANT_IMAGE) {
            menuImage = menu.menuImageUrl
        }

        return RestaurantDetailsResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            imageUrl = menuImage,
            isVerify = restaurant.isVerify
        )
    }
}
