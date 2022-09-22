package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuRepository : CrudRepository<Menu, UUID> {
    fun findAllByRestaurant(restaurant: Restaurant): MutableList<Menu>?

    fun findFirstByRestaurant(restaurant: Restaurant): Menu?

    fun existsByRestaurant(restaurant: Restaurant): Boolean
}
