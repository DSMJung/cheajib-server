package com.cheajib.cheajibserver.domain.review.presentation.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class MyReviewResponse(
    val restaurantName: String,
    val restaurantId: UUID,
    val reviewPoint: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val imageList: List<String>?,
    val ownerCommentResponse: OwnerCommentResponse?
)
