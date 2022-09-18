package com.cheajib.cheajibserver.domain.review.domain.Repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class OwnerCommentVO @QueryProjection constructor(
    val comment: String,
    val createAt: LocalDateTime
)
