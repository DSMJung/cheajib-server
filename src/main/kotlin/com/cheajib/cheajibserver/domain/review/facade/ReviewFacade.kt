package com.cheajib.cheajibserver.domain.review.facade

import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.exception.ReviewNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ReviewFacade(
    private val reviewRepository: ReviewRepository
) {
    fun getReviewById(reviewId: UUID): Review {
        return reviewRepository.findByIdOrNull(reviewId) ?: throw ReviewNotFoundException.EXCEPTION
    }
}