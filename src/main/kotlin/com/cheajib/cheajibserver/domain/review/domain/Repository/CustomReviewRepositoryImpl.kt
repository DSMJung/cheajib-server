package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.comment.domain.QComment.comment1
import com.cheajib.cheajibserver.domain.restaurant.domain.QRestaurant.restaurant
import com.cheajib.cheajibserver.domain.review.domain.QReview.review
import com.cheajib.cheajibserver.domain.review.domain.QReviewImage
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.MyReviewVO
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.QMyReviewVO
import com.cheajib.cheajibserver.domain.review.domain.Repository.vo.QOwnerCommentVO
import com.cheajib.cheajibserver.domain.user.domain.User
import com.querydsl.core.types.Expression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory

class CustomReviewRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CustomReviewRepository {
    override fun queryMyReview(user: User): List<MyReviewVO> {
        return jpaQueryFactory
            .select(
                QMyReviewVO(
                    restaurant.name,
                    restaurant.id,
                    review.reviewPoint,
                    review.content,
                    review.createAt,
                    getImageList(),
                    QOwnerCommentVO(
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
            .fetch().toList()
    }

    private fun getImageList(): Expression<List<String>> {
        val imageList: List<String> = jpaQueryFactory
            .select(QReviewImage.reviewImage.imageUrl)
            .leftJoin(QReviewImage.reviewImage.review, review)
            .fetch()
            .toList()
        return Expressions.constant(imageList)
    }
}
