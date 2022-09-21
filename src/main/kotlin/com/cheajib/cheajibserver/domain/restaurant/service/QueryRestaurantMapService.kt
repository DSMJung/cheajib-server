package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantMapListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantMapResponse
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Suppress("UNREACHABLE_CODE")
@Service
class QueryRestaurantMapService(
    private val restaurantRepository: RestaurantRepository,
    private val reviewFacade: ReviewFacade,
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
            var menuLevel = Level.FLEXITARIAN

            if (menuRepository.existsByRestaurant(restaurant)) {
                val menu = menuRepository.findTop1ByRestaurant(restaurant)
                menuLevel = menuLevelRepository.findByMenu(menu).id.level
            }

           /* if (!restaurantFacade.filter(restaurant, star, level)) {
                continue
            }*/

            return QueryRestaurantMapListResponse(
                RestaurantMapResponse(
                    id = restaurant.id,
                    name = restaurant.name,
                    level = menuLevel
                )
            )
        }
        return throw RestaurantNotFoundException.EXCEPTION
    }
}
