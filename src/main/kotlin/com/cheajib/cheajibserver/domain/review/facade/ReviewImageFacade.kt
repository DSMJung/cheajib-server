package com.cheajib.cheajibserver.domain.review.facade

import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.domain.ReviewImage
import com.cheajib.cheajibserver.domain.review.exception.ReviewImageNotFoundException
import org.springframework.stereotype.Component

@Component
class ReviewImageFacade(
    private val reviewImageRepository: ReviewImageRepository
) {
    fun getAllReviewImageByReview(review: Review): List<ReviewImage> {
        return reviewImageRepository.findAllByReview(review) ?: throw ReviewImageNotFoundException
    }
}
