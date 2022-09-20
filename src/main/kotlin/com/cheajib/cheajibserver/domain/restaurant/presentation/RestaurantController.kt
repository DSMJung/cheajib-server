package com.cheajib.cheajibserver.domain.restaurant.presentation

import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.MapRestaurantListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantMapListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantReviewResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantDetailsService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantMapListService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantPreviewService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantReviewService
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@RequestMapping("/restaurants")
@RestController
class RestaurantController(
    private val queryRestaurantPreviewService: QueryRestaurantPreviewService,
    private val queryRestaurantDetailsService: QueryRestaurantDetailsService,
    private val queryRestaurantReviewService: QueryRestaurantReviewService,
    private val queryRestaurantMapListService: QueryRestaurantMapListService
) {

    @GetMapping("/{restaurant-id}")
    fun queryRestaurantPreview(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): QueryRestaurantResponse {
        return queryRestaurantPreviewService.execute(restaurantId)
    }

    @GetMapping("/details/{restaurant-id}")
    fun queryRestaurantDetails(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): RestaurantDetailsResponse {
        return queryRestaurantDetailsService.execute(restaurantId)
    }

    @GetMapping("/reviews/{restaurant-id}")
    fun queryRestaurantReview(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): QueryRestaurantReviewResponse {
        return queryRestaurantReviewService.execute(restaurantId)
    }

    @GetMapping("/lists")
    fun queryRestaurantMapList(
        @RequestParam(required = false)
        x: Double,
        @RequestParam(required = false)
        y: Double,
        @RequestParam(required = false)
        level: Level,
        @RequestParam(required = false)
        star: Int
    ): QueryRestaurantMapListResponse {
        return queryRestaurantMapListService.execute(x, y, level, star)
    }
}
