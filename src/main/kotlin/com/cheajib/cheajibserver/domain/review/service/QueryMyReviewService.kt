package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.MyReviewListVO
import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyReviewService(
    private val userFacade: UserFacade,
    private val reviewRepository: ReviewRepository
) {
    @Transactional(readOnly = true)
    fun execute(): MyReviewListVO {
        val user: User = userFacade.getCurrentUser()
        val myReviewList = reviewRepository.queryMyReviewList(user)

        return MyReviewListVO(myReviewList)
    }
}
