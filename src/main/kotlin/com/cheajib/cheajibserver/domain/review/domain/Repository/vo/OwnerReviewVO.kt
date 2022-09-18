package com.cheajib.cheajibserver.domain.review.domain.Repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class OwnerReviewVO @QueryProjection constructor(
    val comment: String,
    val createAt: LocalDateTime
)
