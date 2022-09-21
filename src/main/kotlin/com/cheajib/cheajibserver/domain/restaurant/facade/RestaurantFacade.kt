package com.cheajib.cheajibserver.domain.restaurant.facade

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
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
    private val reviewFacade: ReviewFacade,
    private val reviewRepository: ReviewRepository
) {
    fun getRestaurantById(id: UUID): Restaurant {
        return restaurantRepository.findByIdOrNull(id) ?: throw RestaurantNotFoundException.EXCEPTION
    }

    fun filter(restaurant: Restaurant, star: Int, level: Level): Boolean {
        var menuLevel = Level.FLEXITARIAN
        var starPoint = 5.0

        if (reviewRepository.existsByRestaurant(restaurant)) {
            val reviewList = reviewFacade.getAllReviewByRestaurant(restaurant)
            var sum = 0
            for (review in reviewList) {
                sum += review.reviewPoint
            }
            starPoint = (sum / reviewList.size).toDouble()
        }

        if (menuRepository.existsByRestaurant(restaurant)) {
            val menu = menuRepository.findTop1ByRestaurant(restaurant)
            menuLevel = menuLevelRepository.findByMenu(menu).id.level
        }

        if (starPoint < star) {
            return false
        }

        when {
            level.name == Level.VEGAN.name ||
                    menuLevel.name == Level.LACTO.name ||
                    menuLevel.name == Level.LACTO_OVO.name ||
                    menuLevel.name == Level.PESCO.name ||
                    menuLevel.name == Level.POLLO.name ||
                    menuLevel.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.LACTO.name ||
                    menuLevel.name == Level.LACTO_OVO.name ||
                    menuLevel.name == Level.PESCO.name ||
                    menuLevel.name == Level.POLLO.name ||
                    menuLevel.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.LACTO_OVO.name ||
                    menuLevel.name == Level.PESCO.name ||
                    menuLevel.name == Level.POLLO.name ||
                    menuLevel.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.PESCO.name ||
                    menuLevel.name == Level.POLLO.name ||
                    menuLevel.name == Level.FLEXITARIAN.name -> {
                return false
            }
            level.name == Level.POLLO.name || menuLevel.name == Level.FLEXITARIAN.name ->
                return false
        }
        return true
    }
}
