package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.comment.domain.QComment.comment1
import com.cheajib.cheajibserver.domain.restaurant.domain.QRestaurant.restaurant
import com.cheajib.cheajibserver.domain.review.domain.QReview.review
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.MyReviewVO
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.QMyReviewVO
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.QOwnerReviewVO
import com.cheajib.cheajibserver.domain.user.domain.User
import com.querydsl.jpa.impl.JPAQueryFactory

class CustomReviewRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CustomReviewRepository {
    override fun queryMyReviewList(user: User?): List<MyReviewVO>? {
        return jpaQueryFactory
            .select(
                QMyReviewVO(
                    restaurant.name,
                    restaurant.id,
                    review.reviewPoint,
                    review.content,
                    review.createAt,
                    QOwnerReviewVO(
                        comment1.comment,
                        comment1.createAt
                    )
                )
            )
            .from(review)
            .leftJoin(review.restaurant, restaurant)
            .leftJoin(review, comment1.review)
            .where(review.user.eq(user))
            .orderBy(review.createAt.desc())
            .fetch()
    }
}

