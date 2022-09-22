package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantMapListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantMapResponse
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryRestaurantMapService(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantFacade: RestaurantFacade
) {
    @Transactional(readOnly = true)
    fun execute(
        x: Double,
        y: Double,
        level: Level,
        star: Int
    ): QueryRestaurantMapListResponse {
        val restaurantList = restaurantRepository.findAllRestaurant(x, y)

        return QueryRestaurantMapListResponse(
            restaurantsList = restaurantList
                .filter {
                    restaurantFacade.filter(it, star, level)
                }
                .map {
                    RestaurantMapResponse(
                        id = it.id,
                        name = it.name,
                        level = restaurantFacade.getMenuLevel(it),
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                }
        )
    }
}
