package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
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
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository,
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

        for (restaurant in restaurantList) {
            if (!restaurantFacade.filter(restaurant, star, level)) {
                continue
            }
        }

        return QueryRestaurantMapListResponse(
            restaurantsList = restaurantList
                .map {
                    RestaurantMapResponse(
                        id = it.id,
                        name = it.name,
                        level = getMenuLevel(it),
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                }
        )
    }

    private fun getMenuLevel(restaurant: Restaurant): Level {
        if (menuRepository.existsByRestaurant(restaurant)) {
            val menu = menuRepository.findTop1ByRestaurant(restaurant)
            return menuLevelRepository.findByMenu(menu).id.level
        }
        return Level.FLEXITARIAN
    }
}
