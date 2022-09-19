package com.cheajib.cheajibserver.domain.restaurant.presentation

import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantDetailsService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantPreviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/restaurants")
@RestController
class RestaurantController(
    private val queryRestaurantPreviewService: QueryRestaurantPreviewService,
    private val queryRestaurantDetailsService: QueryRestaurantDetailsService
) {

    @GetMapping("/{restaurant-id}")
    fun queryRestaurantPreview(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): QueryRestaurantResponse {
        return queryRestaurantPreviewService.execute(restaurantId)
    }

    @GetMapping("/{restaurant-id}")
    fun queryRestaurantDetails(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): RestaurantDetailsResponse {
        return queryRestaurantDetailsService.execute(restaurantId)
    }
}
