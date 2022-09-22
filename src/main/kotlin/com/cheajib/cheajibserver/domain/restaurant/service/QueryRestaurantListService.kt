package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantListResponse
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryRestaurantListService(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantFacade: RestaurantFacade,
) {
    @Transactional(readOnly = true)
    fun execute(
        x: Double,
        y: Double,
        level: Level,
        star: Int
    ): QueryRestaurantListResponse {
        val lists = restaurantRepository.findAllRestaurant(x, y)

        return QueryRestaurantListResponse(
            restaurantList = lists.filter {
                restaurantFacade.filter(it, star, level)
            }
                .map {
                    RestaurantListResponse(
                        id = it.id,
                        name = it.name,
                        address = it.address,
                        starPoint = restaurantFacade.getStarPoint(it),
                        mainMenu = restaurantFacade.getMainMenu(it),
                        imageUrl = restaurantFacade.getImageUrl(it),
                        isVerify = it.isVerify
                    )
                }
        )
    }
}
