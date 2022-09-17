package com.cheajib.cheajibserver.domain.review.exception

import com.cheajib.cheajibserver.domain.review.exception.properties.ReviewErrorCode
import com.cheajib.cheajibserver.global.error.GlobalException

object ReviewImageNotFoundException : GlobalException(ReviewErrorCode.REVIEW_IMAGE_NOT_FOUND) {
    val EXCEPTION = ReviewImageNotFoundException
}
