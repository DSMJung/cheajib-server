package com.cheajib.cheajibserver.domain.review.domain.Repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import java.util.UUID

class MyReviewVO @QueryProjection constructor(
    val restaurantName: String,
    val restaurant_id: UUID,
    val reviewPoint: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val ownerReviewResponse: OwnerReviewVO
)
