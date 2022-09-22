package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.comment.domain.repository.CommentRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.presentation.dto.response.MyReviewListResponse
import com.cheajib.cheajibserver.domain.review.presentation.dto.response.MyReviewResponse
import com.cheajib.cheajibserver.domain.review.presentation.dto.response.OwnerCommentResponse
import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyReviewService(
    private val userFacade: UserFacade,
    private val reviewRepository: ReviewRepository,
    private val reviewImageRepository: ReviewImageRepository,
    private val commentRepository: CommentRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): MyReviewListResponse {
        val user: User = userFacade.getCurrentUser()
        return MyReviewListResponse(
            reviewRepository.findAllByUser(user)?.map { review ->
                val imageList = reviewImageRepository.findAllByReview(review)?.map { it.imageUrl }
                val comment = commentRepository.findByReview(review)

                MyReviewResponse(
                    restaurantId = review.restaurant.id,
                    restaurantName = review.restaurant.name,
                    reviewPoint = review.reviewPoint,
                    content = review.content,
                    createdAt = review.createAt,
                    imageList = (imageList),
                    ownerCommentResponse = comment?.let {
                        OwnerCommentResponse(
                            comment = it.comment,
                            createAt = it.createAt
                        )
                    }
                )
            }
        )
    }
}
