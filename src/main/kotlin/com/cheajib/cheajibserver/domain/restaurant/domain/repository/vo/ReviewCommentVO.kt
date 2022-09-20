package com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class ReviewCommentVO @QueryProjection constructor(
    val comment: String,
    val createAt: LocalDateTime
)
