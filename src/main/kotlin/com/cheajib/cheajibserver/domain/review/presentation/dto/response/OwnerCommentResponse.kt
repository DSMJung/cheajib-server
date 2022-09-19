package com.cheajib.cheajibserver.domain.review.presentation.dto.response

import java.time.LocalDateTime

data class OwnerCommentResponse(
    val comment: String,
    val createAt: LocalDateTime
)
