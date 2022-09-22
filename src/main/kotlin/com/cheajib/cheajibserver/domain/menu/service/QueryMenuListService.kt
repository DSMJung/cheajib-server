package com.cheajib.cheajibserver.domain.menu.service

import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.menu.presentation.dto.response.MenuElement
import com.cheajib.cheajibserver.domain.menu.presentation.dto.response.MenuListResponse
import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryMenuListService(
    private val restaurantFacade: RestaurantFacade,
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository
) {
    @Transactional(readOnly = true)
    fun execute(restaurantId: UUID): MenuListResponse {
        val restaurant: Restaurant = restaurantFacade.getRestaurantById(restaurantId)

        val menuList: List<MenuElement>? = menuRepository.findAllByRestaurant(restaurant)
            ?.map { menu ->
                var maxLevelCount = 0
                val menuTotalCount: Int = menuLevelRepository.findAllByMenuIdOrderByLevelCount(menu.id)
                    .map { it.levelCount }
                    .reduce { total, num ->
                        maxLevelCount = num
                        total + num
                    }
                val menuLevel: MenuLevel = menuLevelRepository.findByMenu(menu) ?: throw MenuNotFoundException.EXCEPTION

                MenuElement(
                    menuId = menu.id,
                    name = menu.name,
                    description = menu.description,
                    price = menu.price,
                    menuImageUrl = menu.menuImageUrl,
                    average = getAverage(maxLevelCount, menuTotalCount),
                    menuCount = menuTotalCount,
                    level = menuLevel.id.level
                )
            }
        return MenuListResponse(menuList)
    }

    private fun getAverage(max: Int, total: Int): String {
        val average = max.toDouble() / total.toDouble() * 100.0
        return String.format("%.1f", average) + "%"
    }
}
