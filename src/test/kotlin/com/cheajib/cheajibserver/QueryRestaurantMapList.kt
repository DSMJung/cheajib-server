package com.cheajib.cheajibserver

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.repository.RestaurantRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKTReader
import org.mockito.Mock
import java.lang.String.format
import java.util.*

class QueryRestaurantMapList(
    @Mock
    private val restaurantRepository: RestaurantRepository
) {

    @ParameterizedTest
    fun 레스토랑_하나_저장(name: String, url: String, address: String, uuid: UUID) {
        val latitude: Double = 37.51435
        val longitude: Double = 127.1234

        val pointWKT = format("POINT(%s %s)", longitude, latitude)

        val point = WKTReader().read(pointWKT) as Point

        val restaurant: Restaurant = Restaurant(
            id = uuid,
            name = name,
            address = address,
            coordinates = point,
            mainImageUrl = url,
            imageUrl = url,
            isVerify = true
        )

        val restaurantSave = restaurantRepository.save(restaurant)

        assertThat(restaurantSave.name).isEqualTo("restaurant")
    }
}