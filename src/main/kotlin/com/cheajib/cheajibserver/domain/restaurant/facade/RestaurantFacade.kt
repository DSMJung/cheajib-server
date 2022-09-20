package com.cheajib.cheajibserver.domain.restaurant.facade

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import com.cheajib.cheajibserver.domain.restaurant.exception.RestaurantNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

@Component
class RestaurantFacade(
    private val restaurantRepository: RestaurantRepository
) {
    fun getRestaurantById(id: UUID): Restaurant {
        return restaurantRepository.findByIdOrNull(id) ?: throw RestaurantNotFoundException.EXCEPTION
    }

    fun getCoordinatesCalculate(latitude: Double, longitude: Double, bearing: Double, distance: Double): Location {
        val radianLatitude: Double = toRadian(latitude)
        val radianLongitude: Double = toRadian(longitude)
        val radianAngle: Double = toRadian(bearing)
        val distanceRadius: Double = distance / 6371.01

        val latitude: Double = Math.asin(sin(radianLatitude) * cos(distanceRadius) +
                cos(radianLatitude) * sin(distanceRadius) * cos(radianAngle))

        var longitude: Double = radianLongitude + Math.atan2(sin(radianAngle) * sin(distanceRadius) *
                cos(radianLatitude), cos(distanceRadius) - sin(radianLatitude) * sin(latitude))

        longitude = normalizeLongitude(longitude)

        return Location(toDegree(latitude), toDegree(longitude))

    }

    private fun toRadian(coordinate: Double): Double {
        return coordinate * Math.PI / 180.0
    }

    private fun toDegree(coordinate: Double): Double {
        return coordinate * 180.0 / Math.PI
    }
    private fun normalizeLongitude(longitude: Double): Double {
        return (longitude + 540) % 360 - 180
    }

    data class Location(
        var longitude:Double,
        var latitude: Double
    )
}
