package com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class ReviewVO @QueryProjection constructor(
    val reviewPoint: Int,
    val content: String,
    val createAt: LocalDateTime,
    val imageList: List<String>,
    val reviewCommentVO: ReviewCommentVO
)
