package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantListResponse
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Suppress("UNREACHABLE_CODE")
@Service
class QueryRestaurantListService(
    private val restaurantRepository: RestaurantRepository,
    private val reviewFacade: ReviewFacade,
    private val menuRepository: MenuRepository,
    private val restaurantFacade: RestaurantFacade,
    private val reviewRepository: ReviewRepository,
    private val menuLevelRepository: MenuLevelRepository
) {
    @Transactional(readOnly = true)
    fun execute(
        x: Double,
        y: Double,
        level: Level,
        star: Int
    ): QueryRestaurantListResponse {
        val restaurantList = restaurantRepository.findAllRestaurant(x, y)

        for (restaurant in restaurantList) {
            var starPoint = 5.0
            var mainMenu = ""

            if (reviewRepository.existsByRestaurant(restaurant)) {
                val review = reviewFacade.getReviewByRestaurant(restaurant)
                val menu = menuRepository.findTop1ByRestaurant(restaurant)
                mainMenu = menu.name
                starPoint = review.reviewPoint / 5.0
            }

            if (!restaurantFacade.filter(restaurant, star, level)) {
                continue
            }

            return QueryRestaurantListResponse(
                RestaurantListResponse(
                    id = restaurant.id,
                    name = restaurant.name,
                    address = restaurant.address,
                    starPoint = starPoint,
                    mainMenu = mainMenu,
                    imageUrl = restaurant.imageUrl,
                    isVerify = restaurant.isVerify
                )
            )
        }
        return throw RestaurantNotFoundException.EXCEPTION
    }
}
