package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.comment.domain.repository.CommentRepository
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantReviewResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryReviewCommentResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryReviewResponse
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryRestaurantReviewService(
    private val restaurantFacade: RestaurantFacade,
    private val reviewFacade: ReviewFacade,
    private val reviewRepository: ReviewRepository,
    private val reviewImageRepository: ReviewImageRepository,
    private val commentRepository: CommentRepository
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): QueryRestaurantReviewResponse {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)
        val starCount = mutableListOf(0, 0, 0, 0, 0)
        var reviewCount = 0
        val totalReview = reviewFacade.getAllReviewByRestaurant(restaurant)
            .map { review ->
                when (review.reviewPoint) {
                    1 -> starCount[0] += 1
                    2 -> starCount[1] += 1
                    3 -> starCount[2] += 1
                    4 -> starCount[3] += 1
                    5 -> starCount[4] += 1
                }
                reviewCount += 1
                review.reviewPoint
            }.reduce { total, num ->
                total + num
            }


        return QueryRestaurantReviewResponse(
            averageStar = (totalReview / reviewCount).toDouble(),
            starCount = starCount,
            reviewList = reviewRepository.findAllByRestaurant(restaurant)
                ?.map { review ->
                    val imageList = reviewImageRepository.findAllByReview(review)?.map { it.imageUrl }
                    val comment = commentRepository.findByReview(review)

                    QueryReviewResponse(
                        reviewPoint = review.reviewPoint,
                        createAt = review.createAt,
                        content = review.content,
                        imageList = (imageList),
                        reviewComment = comment?.let {
                            QueryReviewCommentResponse(
                                comment = it.comment,
                                createAt = it.createAt
                            )
                        }
                    )
                }
        )
    }
}
