package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
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
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val reviewList = reviewFacade.getAllReviewByRestaurant(restaurant)

        var sum = 0
        for (review in reviewList) {
            sum += review.reviewPoint
        }
        val starPoint = (sum / reviewList.size).toDouble()

        var imageUrl = DefaultImage.RESTAURANT_IMAGE
        var mainMenuName = ""

        if (menuRepository.existsByRestaurant(restaurant)) {
            val mainMenu = menuRepository.findTop1ByRestaurant(restaurant)
            mainMenuName = mainMenu.name
            if (restaurant.imageUrl == DefaultImage.RESTAURANT_IMAGE) {
                imageUrl = mainMenu.menuImageUrl
            }
        }

        return QueryRestaurantResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            starPoint = starPoint,
            imageUrl = imageUrl,
            mainMenu = mainMenuName,
            isVerify = restaurant.isVerify
        )
    }
}
