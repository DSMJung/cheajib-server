package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.MainMenuListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryRestauranPreviewService(
    val restaurantFacade: RestaurantFacade,
    val menuRepository: MenuRepository,
    val reviewRepository: ReviewRepository
) {

    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantResponse {
        val restaurant: Restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val review: Review = reviewRepository.findAllByRestaurant(restaurant)

        val starAverage: Int = review.reviewPoint / 5

        val mainMenuList: List<MainMenuListResponse> = menuRepository.findAllByRestaurant(restaurant)
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
