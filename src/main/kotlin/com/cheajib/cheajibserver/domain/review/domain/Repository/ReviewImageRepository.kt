package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.domain.ReviewImage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReviewImageRepository : CrudRepository<ReviewImage, UUID> {
    fun findAllByReview(review: Review): ReviewImage?
}
