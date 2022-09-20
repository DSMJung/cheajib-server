package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

import java.time.LocalDateTime

data class QueryReviewCommentResponse(
    val comment: String,
    val createAt: LocalDateTime
)
