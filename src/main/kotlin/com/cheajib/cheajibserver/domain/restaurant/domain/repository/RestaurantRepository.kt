package com.cheajib.cheajibserver.domain.restaurant.domain.repository

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RestaurantRepository : CrudRepository<Restaurant, UUID> {
}
