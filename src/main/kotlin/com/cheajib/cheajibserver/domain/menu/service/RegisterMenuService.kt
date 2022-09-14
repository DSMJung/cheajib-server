package com.cheajib.cheajibserver.domain.menu.service

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.presentation.dto.request.RegisterMenuRequest
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RegisterMenuService(
    private val menuRepository: MenuRepository,
    private val restaurantFacade: RestaurantFacade
) {
    fun execute(restaurantId: UUID, request: RegisterMenuRequest) {
        val restaurant = restaurantFacade.findRestaurantById(restaurantId)

        val menu = Menu(
            id = UUID(0,0),
            name = request.name,
            price = request.price,
            description = request.description,
            menuImageUrl = request.menuImageUrl,
            restaurant = restaurant
        )
        menuRepository.save(menu)
    }
}
