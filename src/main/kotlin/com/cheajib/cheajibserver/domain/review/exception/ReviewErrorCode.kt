package com.cheajib.cheajibserver.domain.review.exception

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class ReviewErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {
    REVIEW_NOT_FOUND(404, "Review Not Found")
}
