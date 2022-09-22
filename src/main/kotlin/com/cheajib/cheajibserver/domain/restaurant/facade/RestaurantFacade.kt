package com.cheajib.cheajibserver.domain.restaurant.facade

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.menu.facade.MenuLevelFacade
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class RestaurantFacade(
    private val restaurantRepository: RestaurantRepository,
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository,
    private val reviewFacade: ReviewFacade,
    private val reviewRepository: ReviewRepository,
    private val menuFacade: MenuFacade,
    private val menuLevelFacade: MenuLevelFacade
) {
    fun getRestaurantById(id: UUID): Restaurant {
        return restaurantRepository.findByIdOrNull(id) ?: throw RestaurantNotFoundException.EXCEPTION
    }

    fun filter(restaurant: Restaurant, star: Int, level: Level): Boolean {
        val menuLevel = getMenuLevel(restaurant)
        val starPoint = getStarPoint(restaurant)

        if (starPoint < star) {
            return false
        }

        return menuLevel <= level
    }

    fun getImageUrl(restaurant: Restaurant): String {
        val menu = menuRepository.findByRestaurant(restaurant)
        if (menu != null) {
            return menu.menuImageUrl
        }
        return DefaultImage.MENU_IMAGE
    }

    fun getStarPoint(restaurant: Restaurant): Double {
        val reviewList = reviewRepository.findAllByRestaurant(restaurant)

        if (reviewList != null) {
            var sum = 0
            for (review in reviewList) {
                sum += review.reviewPoint
            }
            return (sum / reviewList.size).toDouble()
        }
        return 5.0
    }

    fun getMainMenu(restaurant: Restaurant): String {
        val menu = menuRepository.findByRestaurantOrderById(restaurant)
        if (menu != null) {
            return menu.name
        }
        return ""
    }

    fun getMenuLevel(restaurant: Restaurant): Level {
        val menu = menuRepository.findByRestaurant(restaurant)
        if (menu != null) {
            return menuLevelFacade.getMenuLevelByMenu(menu).id.level
        }
        return Level.FLEXITARIAN
    }
}
