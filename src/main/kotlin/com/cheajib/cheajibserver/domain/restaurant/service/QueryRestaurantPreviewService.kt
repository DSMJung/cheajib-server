package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.MainMenuListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryRestaurantPreviewService(
    val restaurantFacade: RestaurantFacade,
    val reviewFacade: ReviewFacade,
    val menuRepository: MenuRepository,
) {

    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantResponse {
        val restaurant: Restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val review: Review = reviewFacade.getReviewByRestaurant(restaurant)

        val starAverage: Int = review.reviewPoint / 5

        val mainMenuList: List<MainMenuListResponse> = menuRepository.findTop3ByRestaurant(restaurant)
            ?.map {
                MainMenuListResponse(
                    mainMenu = it.menuImageUrl
                )
            }?.toList() ?: throw MenuNotFoundException.EXCEPTION

        return QueryRestaurantResponse(
            restaurantName = restaurant.name,
            address = restaurant.address,
            starPoint = starAverage,
            mainImageUrl = restaurant.mainImageUrl,
            mainMenuList = mainMenuList,
            isVerify = restaurant.isVerify
        )
    }
}
