package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevelId
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.domain.ReviewImage
import com.cheajib.cheajibserver.domain.review.domain.ReviewImageId
import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class WriteReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewImageRepository: ReviewImageRepository,
    private val menuLevelRepository: MenuLevelRepository,
    private val userFacade: UserFacade,
    private val restaurantFacade: RestaurantFacade
) {
    @Transactional
    fun execute(restaurantId: UUID, request: WriteReviewRequest) {
        val user = userFacade.getCurrentUser()
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)

        for (menuElement in request.menuList) {
            val menuLevel: MenuLevel? = menuLevelRepository.findById(
                MenuLevelId(id = menuElement.menuId, level = menuElement.level)
            )
            menuLevel?.plusLevelCount()
        }

        val review = reviewRepository.save(
            Review(
                id = UUID.randomUUID(),
                createAt = LocalDateTime.now(),
                reviewPoint = request.reviewPoint,
                content = request.content,
                user = user,
                restaurant = restaurant
            )
        )

        for (image in request.imageUrl) {
            var i = 1
            val reviewImage = ReviewImage(
                id = ReviewImageId(
                    sequence = i,
                    id = review.id
                ),
                imageUrl = image,
                review = review
            )
            reviewImageRepository.save(reviewImage)
            i += 1
        }
    }
}
