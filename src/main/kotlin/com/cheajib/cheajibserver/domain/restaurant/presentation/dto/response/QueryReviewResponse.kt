package com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response

import java.time.LocalDateTime

data class QueryReviewResponse(
    val name: String,
    val reviewPoint: Int,
    val content: String,
    val createAt: LocalDateTime,
    val imageList: List<String>?,
    val reviewComment: QueryReviewCommentResponse?
)
