package com.cheajib.cheajibserver.domain.comment.presentation

import com.cheajib.cheajibserver.domain.comment.presentation.dto.request.WriteCommentReviewRequest
import com.cheajib.cheajibserver.domain.comment.service.WriteCommentReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/comments")
@RestController
class CommentController(
    private val writeCommentReviewService: WriteCommentReviewService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{review-id}")
    fun writeCommentReview(
        @PathVariable("review-id")
        reviewId: UUID,
        @RequestBody
        @Valid
        request: WriteCommentReviewRequest
    ) {
        writeCommentReviewService.execute(reviewId, request)
    }
}
