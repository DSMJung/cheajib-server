package com.cheajib.cheajibserver.domain.restaurant.facade

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class RestaurantFacade(
    private val restaurantRepository: RestaurantRepository,
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository,
    private val reviewFacade: ReviewFacade
) {
    fun getRestaurantById(id: UUID): Restaurant {
        return restaurantRepository.findByIdOrNull(id) ?: throw RestaurantNotFoundException.EXCEPTION
    }

    fun filter(restaurant: Restaurant, star: Int, level: Level): Boolean {
        val review = reviewFacade.getReviewByRestaurant(restaurant)
        val starPoint = review.reviewPoint / 5.0

        val menu = menuRepository.findTop1ByRestaurant(restaurant) ?: throw MenuNotFoundException.EXCEPTION
        val menuLevel = menuLevelRepository.findByMenu(menu)

        if (starPoint < star) {
            return false
        }

        when {
            level.name == Level.VEGAN.name ||
                    menuLevel.id.level.name == Level.LACTO.name ||
                    menuLevel.id.level.name == Level.LACTO_OVO.name ||
                    menuLevel.id.level.name == Level.PESCO.name ||
                    menuLevel.id.level.name == Level.POLLO.name ||
                    menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.LACTO.name ||
                    menuLevel.id.level.name == Level.LACTO_OVO.name ||
                    menuLevel.id.level.name == Level.PESCO.name ||
                    menuLevel.id.level.name == Level.POLLO.name ||
                    menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.LACTO_OVO.name ||
                    menuLevel.id.level.name == Level.PESCO.name ||
                    menuLevel.id.level.name == Level.POLLO.name ||
                    menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.PESCO.name ||
                    menuLevel.id.level.name == Level.POLLO.name ||
                    menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.POLLO.name || menuLevel.id.level.name == Level.FLEXITARIAN.name ->
                return false
        }
        return true
    }
}
