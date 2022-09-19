package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.MyReviewVO
import com.cheajib.cheajibserver.domain.user.domain.User

interface CustomReviewRepository {
    fun queryMyReview(user: User): List<MyReviewVO>
}
