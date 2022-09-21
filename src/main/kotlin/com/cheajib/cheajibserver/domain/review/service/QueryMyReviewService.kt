package com.cheajib.cheajibserver.domain.review.service

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
    private val reviewRepository: ReviewRepository
) {
    @Transactional(readOnly = true)
    fun execute(): MyReviewListResponse {
        val user: User = userFacade.getCurrentUser()
        return MyReviewListResponse(
            myReviewList = reviewRepository.queryMyReview(user)
                .map {
                    MyReviewResponse(
                        restaurantName = it.restaurantName,
                        restaurantId = it.restaurantId,
                        reviewPoint = it.reviewPoint,
                        content = it.content,
                        createdAt = it.createdAt,
                        ownerCommentResponse = OwnerCommentResponse(
                            comment = it.ownerCommentVO.comment,
                            createAt = it.ownerCommentVO.createAt
                        )
                    )
                }.toList()
        )
    }
}
