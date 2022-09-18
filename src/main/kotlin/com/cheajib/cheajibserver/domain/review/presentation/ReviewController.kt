package com.cheajib.cheajibserver.domain.review.presentation

import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.review.service.QueryMyReviewService
import com.cheajib.cheajibserver.domain.review.service.WriteReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
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
    private val queryMyReviewService: QueryMyReviewService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurant-id}")
    fun writeReview(
        @PathVariable("restaurant-id")
        restaurantId: UUID,
        @Valid
        @RequestBody
        request: WriteReviewRequest
    ) {
        writeReviewService.execute(restaurantId, request)
    }

    @GetMapping
    fun queryMyReviewList() {
       queryMyReviewService.execute()
    }
}
