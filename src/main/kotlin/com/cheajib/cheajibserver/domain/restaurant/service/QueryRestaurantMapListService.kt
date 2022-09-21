package com.cheajib.cheajibserver.domain.restaurant.service

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.restaurant.domain.type.Direction
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade
import com.cheajib.cheajibserver.domain.restaurant.facade.RestaurantFacade.*
import com.cheajib.cheajibserver.domain.restaurant.presentation.dto.response.QueryRestaurantMapListResponse
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.String
import javax.persistence.EntityManager

@Service
class QueryRestaurantMapListService(
    private val restaurantFacade: RestaurantFacade,
    private val em: EntityManager
) {

    @Transactional(readOnly = true)
    fun execute(x: Double, y: Double, level: Level, star: Int): QueryRestaurantMapListResponse {
        val northEast: Location = restaurantFacade.getCoordinatesCalculate(x, y, distance = 123.1,
            bearing = Direction.NORTHEAST.bearing
        )

        val southWest: Location = restaurantFacade.getCoordinatesCalculate(x, y, distance = 12324.1,
            bearing = Direction.SOUTHWEST.bearing
        )

        val x1: Double = northEast.latitude
        val y1: Double = northEast.longitude
        val x2: Double = southWest.longitude
        val y2: Double = southWest.longitude

        val pointFormat = String.format("'LINESTRING(%f %f, %f %f)')", x1, y1, x2, y2)
        val query = em.createNativeQuery(
            "SELECT r.id, r.address, r.cordinates, r.mainImageUrl, r.imageUrl, r.isVerify"
                    + "FROM restaurant AS r "
                    + "WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + pointFormat + ", r.point)", Restaurant::class.java
        )
            .setMaxResults(10)

        val restaurants: QueryRestaurantMapListResponse = query.resultList
        return restaurants
    }


}