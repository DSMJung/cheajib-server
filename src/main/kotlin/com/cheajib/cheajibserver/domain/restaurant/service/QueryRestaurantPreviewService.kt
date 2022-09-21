package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantPreviewService(
    val restaurantFacade: RestaurantFacade,
    val reviewFacade: ReviewFacade,
    val menuRepository: MenuRepository,
) {

    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantResponse {
        val restaurant: Restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val reviewList = reviewFacade.getAllReviewByRestaurant(restaurant)

        var sum = 0
        for (review in reviewList) {
            sum += review.reviewPoint
        }
        val starPoint = (sum / reviewList.size).toDouble()

        val mainMenu = menuRepository.findTop1ByRestaurant(restaurant) ?: throw MenuNotFoundException.EXCEPTION

        return QueryRestaurantResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            starPoint = starPoint,
            imageUrl = restaurant.imageUrl,
            mainMenuList = mainMenu.name,
            isVerify = restaurant.isVerify
        )
    }
}
