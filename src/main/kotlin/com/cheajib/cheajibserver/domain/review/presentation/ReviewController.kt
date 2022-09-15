package com.cheajib.cheajibserver.domain.review.presentation

import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.review.service.WriteReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/review")
@RestController
class ReviewController(
    private val writeReviewService: WriteReviewService
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
}
