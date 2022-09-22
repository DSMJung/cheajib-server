package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuRepository : CrudRepository<Menu, UUID> {
    fun findAllByRestaurant(restaurant: Restaurant): MutableList<Menu>?

    fun findByRestaurantOrderById(restaurant: Restaurant): Menu?

    fun findByRestaurant(restaurant: Restaurant): Menu?

    fun findFirstByRestaurant(restaurant: Restaurant): Menu?

    fun existsByRestaurant(restaurant: Restaurant): Boolean

    fun findTop1ByRestaurant(restaurant: Restaurant): Menu?
}
