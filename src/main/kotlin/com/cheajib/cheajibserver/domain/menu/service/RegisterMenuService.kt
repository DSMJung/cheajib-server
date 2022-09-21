package com.cheajib.cheajibserver.domain.menu.service

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevelId
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.presentation.dto.request.RegisterMenuRequest
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RegisterMenuService(
    private val menuRepository: MenuRepository,
    private val restaurantFacade: RestaurantFacade,
    private val menuLevelRepository: MenuLevelRepository
) {
    @Transactional
    fun execute(restaurantId: UUID, request: RegisterMenuRequest) {
        val restaurant = restaurantFacade.getRestaurantById(restaurantId)

        val menu = Menu(
            id = UUID(0, 0),
            name = request.name,
            price = request.price,
            description = request.description,
            menuImageUrl = request.menuImageUrl,
            restaurant = restaurant
        )
        menuRepository.save(menu)

        val levelList = listOf(
            Level.VEGAN,
            Level.LACTO,
            Level.LACTO_OVO,
            Level.PESCO,
            Level.POLLO,
            Level.FLEXITARIAN
        )
        for (level: Level in levelList) {
            val menuLevel = MenuLevel(
                id = MenuLevelId(
                    id = menu.id,
                    level = (level)
                ),
                menu = menu,
                levelCount = if (level == request.level) 1 else 0
            )
            menuLevelRepository.save(menuLevel)
        }
    }
}
