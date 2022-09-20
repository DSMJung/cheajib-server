package com.cheajib.cheajibserver.domain.restaurant.domain.repository

import com.cheajib.cheajibserver.domain.comment.domain.QComment.comment1
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo.QReviewCommentVO
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo.QReviewVO
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.vo.ReviewVO
import com.cheajib.cheajibserver.domain.review.domain.QReview.review
import com.cheajib.cheajibserver.domain.review.domain.QReviewImage.reviewImage
import com.querydsl.core.types.Expression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory

class CustomRestaurantRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CustomRestaurantRepository {
    override fun queryReview(restaurant: Restaurant): List<ReviewVO> {
        return jpaQueryFactory.select(
            QReviewVO(
                review.reviewPoint,
                review.content,
                review.createAt,
                getImageUrl(),
                QReviewCommentVO(
                    comment1.comment,
                    comment1.createAt
                )
            )
        ).from(review)
            .leftJoin(review, comment1.review)
            .orderBy(review.createAt.desc())
            .fetch()
    }

    private fun getImageUrl(): Expression<List<String>> {
        val imageUrl: List<String> = jpaQueryFactory
            .select(reviewImage.imageUrl)
            .leftJoin(reviewImage.review, review)
            .fetch()
            .toList()
        return Expressions.constant(imageUrl)
    }
}
