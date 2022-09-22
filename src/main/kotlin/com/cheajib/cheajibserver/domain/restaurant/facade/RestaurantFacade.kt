package com.cheajib.cheajibserver.domain.restaurant.facade

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
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
    private val reviewRepository: ReviewRepository
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
        if (menuRepository.existsByRestaurant(restaurant)) {
            val mainMenu = menuRepository.findTop1ByRestaurant(restaurant)
            return mainMenu.menuImageUrl
        }
        return DefaultImage.RESTAURANT_IMAGE
    }

    fun getStarPoint(restaurant: Restaurant): Double {
        if (reviewRepository.existsByRestaurant(restaurant)) {
            val reviewList = reviewFacade.getAllReviewByRestaurant(restaurant)

            var sum = 0
            for (review in reviewList) {
                sum += review.reviewPoint
            }
            return (sum / reviewList.size).toDouble()
        }
        return 5.0
    }

    fun getMainMenu(restaurant: Restaurant): String {
        if (menuRepository.existsByRestaurant(restaurant)) {
            val menu = menuRepository.findTop1ByRestaurant(restaurant)
            return menu.name
        }
        return "물"
    }

    fun getMenuLevel(restaurant: Restaurant): Level {
        if (menuRepository.existsByRestaurant(restaurant)) {
            val menu = menuRepository.findTop1ByRestaurant(restaurant)
            return menuLevelRepository.findByMenu(menu).id.level
        }
        return Level.FLEXITARIAN
    }
}
