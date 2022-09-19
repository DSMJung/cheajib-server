package com.cheajib.cheajibserver.domain.review.domain.Repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import java.util.UUID

class MyReviewVO @QueryProjection constructor(
    val restaurantName: String,
    val restaurantId: UUID,
    val reviewPoint: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val ownerCommentVO: OwnerCommentVO
)
