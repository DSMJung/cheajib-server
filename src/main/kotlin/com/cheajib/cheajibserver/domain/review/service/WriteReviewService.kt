package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.domain.ReviewImage
import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class WriteReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewImageRepository: ReviewImageRepository,
    private val menuLevelRepository: MenuLevelRepository,
    private val userFacade: UserFacade,
    private val menuFacade: MenuFacade,
    private val restaurantFacade: RestaurantFacade
) {
    @Transactional
    fun execute(restaurantId: UUID, request: WriteReviewRequest) {
        val user = userFacade.getCurrentUser()
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)

        for (menuElement in request.menuList) {
            val menu = menuFacade.getMenuById(menuElement.menuId)

            val menuLevel = MenuLevel(
                id = menu.id,
                menu = menu,
                level = menuElement.level,
                levelCount = 0
            )
            menuLevelRepository.save(menuLevel)
        }

        val review = Review(
            id = UUID(0, 0),
            createAt = LocalDateTime.now(),
            reviewPoint = request.reviewPoint,
            content = request.content,
            user = user,
            restaurant = restaurant
        )
        reviewRepository.save(review)

        for (image in request.imageUrl) {
            val reviewImage = ReviewImage(
                id = UUID(0, 0),
                imageUrl = image,
                review = review
            )
            reviewImageRepository.save(reviewImage)
        }
    }
}
