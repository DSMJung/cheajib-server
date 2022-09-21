package com.cheajib.cheajibserver.domain.restaurant.presentation

import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantInfoResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantListResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantReviewResponse
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.RestaurantDetailsResponse
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantDetailsService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantInfoService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantListService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantPreviewService
import com.cheajib.cheajibserver.domain.restaurant.service.QueryRestaurantReviewService
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/restaurants")
@RestController
class RestaurantController(
    private val queryRestaurantPreviewService: QueryRestaurantPreviewService,
    private val queryRestaurantDetailsService: QueryRestaurantDetailsService,
    private val queryRestaurantReviewService: QueryRestaurantReviewService,
    private val queryRestaurantInfoService: QueryRestaurantInfoService,
    private val queryRestaurantListService: QueryRestaurantListService
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

    @GetMapping("/info/{restaurant-id}")
    fun queryRestaurantInfo(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): QueryRestaurantInfoResponse {
        return queryRestaurantInfoService.execute(restaurantId)
    }

    @GetMapping("/lists")
    fun queryRestaurantList(
        @RequestParam(value = "x")
        x: Double,
        @RequestParam(value = "y")
        y: Double,
        @RequestParam(value = "level")
        level: Level,
        @RequestParam(value = "star")
        star: Int
        ): QueryRestaurantListResponse {
        return queryRestaurantListService.execute(x, y, level, star)
    }
}
