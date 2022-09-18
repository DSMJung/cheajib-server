package com.cheajib.cheajibserver.domain.comment.service

import com.cheajib.cheajibserver.domain.comment.domain.Comment
import com.cheajib.cheajibserver.domain.comment.domain.repository.CommentRepository
import com.cheajib.cheajibserver.domain.comment.presentation.dto.request.WriteCommentReviewRequest
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.exception.ReviewNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class WriteCommentReviewService(
    private val reviewRepository: ReviewRepository,
    private val commentRepository: CommentRepository
) {
    fun execute(reviewId: UUID, request: WriteCommentReviewRequest) {
        val review: Review =
            reviewRepository.findByIdOrNull(reviewId) ?: throw ReviewNotFoundException.EXCEPTION

        val reviewComment = Comment(
            id = UUID(0, 0),
            createAt = LocalDateTime.now(),
            comment = request.comment,
            review = review
        )
        commentRepository.save(reviewComment)
    }
}
