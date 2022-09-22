package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReviewRepository : CrudRepository<Review, UUID>, CustomReviewRepository {
    fun findAllByRestaurant(restaurant: Restaurant): List<Review>?

    fun existsByRestaurant(restaurant: Restaurant): Boolean

    fun findByRestaurant(restaurant: Restaurant): Review?

    fun findAllByUser(user: User): List<Review>?
}
