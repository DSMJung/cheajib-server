package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuRepository : CrudRepository<Menu, UUID> {
    fun findAllByRestaurant(restaurant: Restaurant): MutableList<Menu>?

    fun findTop3ByRestaurant(restaurant: Restaurant): MutableList<Menu>?
    
    fun findByRestaurantOrderById(restaurant: Restaurant): Menu?
}
