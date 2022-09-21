package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantListResponse
import com.cheajib.cheajibserver.domain.review.facade.ReviewFacade
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Suppress("UNREACHABLE_CODE")
@Service
class QueryRestaurantListService(
    private val restaurantRepository: RestaurantRepository,
    private val reviewFacade: ReviewFacade,
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository
) {
    @Transactional(readOnly = true)
    fun execute(
        x: Double,
        y: Double,
        level: Level,
        star: Int
    ): QueryRestaurantListResponse {
        val restaurantList = restaurantRepository.findAllRestaurant(x, y)

        for (restaurant in restaurantList) {
            val review = reviewFacade.getReviewByRestaurant(restaurant)
            val starPoint = review.reviewPoint / 5.0

            val menu = menuRepository.findTop1ByRestaurant(restaurant) ?: throw MenuNotFoundException.EXCEPTION
            val menuLevel = menuLevelRepository.findByMenu(menu)


            if (starPoint < star) {
                continue
            }

            when {
                level.name == Level.VEGAN.name ||
                        menuLevel.id.level.name == Level.LACTO.name ||
                        menuLevel.id.level.name == Level.LACTO_OVO.name ||
                        menuLevel.id.level.name == Level.PESCO.name ||
                        menuLevel.id.level.name == Level.POLLO.name ||
                        menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                    continue
                }

                level.name == Level.LACTO.name ||
                        menuLevel.id.level.name == Level.LACTO_OVO.name ||
                        menuLevel.id.level.name == Level.PESCO.name ||
                        menuLevel.id.level.name == Level.POLLO.name ||
                        menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                    continue
                }

                level.name == Level.LACTO_OVO.name ||
                        menuLevel.id.level.name == Level.PESCO.name ||
                        menuLevel.id.level.name == Level.POLLO.name ||
                        menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                    continue
                }

                level.name == Level.PESCO.name ||
                        menuLevel.id.level.name == Level.POLLO.name ||
                        menuLevel.id.level.name == Level.FLEXITARIAN.name -> {
                    continue
                }

                level.name == Level.POLLO.name || menuLevel.id.level.name == Level.FLEXITARIAN.name ->
                    continue
            }

            return QueryRestaurantListResponse(
                RestaurantListResponse(
                    id = restaurant.id,
                    name = restaurant.name,
                    address = restaurant.address,
                    starPoint = starPoint,
                    mainMenu = menu.name,
                    imageUrl = restaurant.imageUrl,
                    isVerify = restaurant.isVerify
                )
            )
        }
        return throw RestaurantNotFoundException.EXCEPTION
    }
}
