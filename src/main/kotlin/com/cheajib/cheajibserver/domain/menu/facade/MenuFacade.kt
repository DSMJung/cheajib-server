package com.cheajib.cheajibserver.domain.menu.facade

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MenuFacade(
    private val menuRepository: MenuRepository
) {
    fun getMenuById(id: UUID): Menu {
        return menuRepository.findByIdOrNull(id) ?: throw MenuNotFoundException.EXCEPTION
    }

    fun getMenuByRestaurant(restaurant: Restaurant): Menu {
        return menuRepository.findFirstByRestaurant(restaurant) ?: throw MenuNotFoundException.EXCEPTION
    }
}
