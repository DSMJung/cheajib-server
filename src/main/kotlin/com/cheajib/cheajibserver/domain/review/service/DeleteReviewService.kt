package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewImageRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.review.facade.ReviewImageFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewImageRepository: ReviewImageRepository,
    private val reviewFacade: ReviewFacade,
    private val reviewImageFacade: ReviewImageFacade
) {
    @Transactional
    fun execute(reviewId: UUID) {
        val review = reviewFacade.getReviewById(reviewId)
        val reviewImage = reviewImageFacade.getReviewImageByReview(review)
        reviewImageRepository.delete(reviewImage)
        reviewRepository.delete(review)
    }
}
