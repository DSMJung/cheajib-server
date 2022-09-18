package com.cheajib.cheajibserver.domain.review.presentation

import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.review.service.DeleteReviewService
import com.cheajib.cheajibserver.domain.review.service.WriteReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/review")
@RestController
class ReviewController(
    private val writeReviewService: WriteReviewService,
    private val deleteReviewService: DeleteReviewService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun writeReview(
        @Valid
        @RequestBody
        request: WriteReviewRequest
    ) {
        writeReviewService.execute(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    fun deleteReview(
        @PathVariable("review-id")
        reviewId: UUID
    ) {
        deleteReviewService.execute(reviewId)
    }
}
