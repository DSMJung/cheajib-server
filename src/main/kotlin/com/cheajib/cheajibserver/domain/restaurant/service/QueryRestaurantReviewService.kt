package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantReviewResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryReviewCommentResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryReviewResponse
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantReviewService(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantFacade: RestaurantFacade,
    private val reviewFacade: ReviewFacade
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantReviewResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val starCount = mutableListOf(0, 0, 0, 0, 0)
        var reviewCount = 0
        val review = reviewFacade.getAllReviewByRestaurant(restaurant)
            .map { review ->
                when (review.reviewPoint) {
                    1 -> starCount.add(0, starCount[0] + 1)
                    2 -> starCount.add(1, starCount[1] + 1)
                    3 -> starCount.add(2, starCount[2] + 1)
                    4 -> starCount.add(3, starCount[3] + 1)
                    5 -> starCount.add(4, starCount[4] + 1)
                }
                reviewCount += 1
                review.reviewPoint
            }.reduce { total, num ->
                total + num
            }


        return QueryRestaurantReviewResponse(
            averageStar = (review / reviewCount).toDouble(),
            starCount = starCount,
            reviewList = restaurantRepository.queryReview(restaurant)?.map {
                QueryReviewResponse(
                    reviewPoint = it.reviewPoint,
                    content = it.content,
                    createAt = it.createAt,
                    imageList = it.imageList,
                    reviewComment = QueryReviewCommentResponse(
                        comment = it.reviewCommentVO.comment, createAt = it.reviewCommentVO.createAt
                    )
                )
            }
        )
    }
}
