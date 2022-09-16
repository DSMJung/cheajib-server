package com.cheajib.cheajibserver.domain.review.exception

import com.cheajib.cheajibserver.global.error.GlobalException

object ReviewNotFoundException : GlobalException(ReviewErrorCode.REVIEW_NOT_FOUND) {
    val EXCEPTION = ReviewNotFoundException
}