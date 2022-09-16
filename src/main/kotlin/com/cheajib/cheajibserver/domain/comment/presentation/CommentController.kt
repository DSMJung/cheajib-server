package com.cheajib.cheajibserver.domain.comment.presentation

import com.cheajib.cheajibserver.domain.comment.presentation.dto.request.WriteCommentReviewRequest
import com.cheajib.cheajibserver.domain.comment.service.WriteCommentReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RequestMapping("/comments")
@RestController
class CommentController(
    private val writeCommentReviewService: WriteCommentReviewService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{review-id}")
    fun writeCommentReview(
        @PathVariable("review-id") reviewId: UUID,
        @RequestBody @Valid request: WriteCommentReviewRequest
    ) {
        writeCommentReviewService.execute(reviewId, request)
    }
}
