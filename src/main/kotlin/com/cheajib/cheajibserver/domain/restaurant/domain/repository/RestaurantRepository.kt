package com.cheajib.cheajibserver.domain.restaurant.domain.repository

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RestaurantRepository : CrudRepository<Restaurant, UUID>, CustomRestaurantRepository {
    @Query("SELECT * FROM tbl_restaurant\n" +
            "  HAVING (6371*acos(cos(radians(:x))*cos(radians(tbl_restaurant.latitude))*cos(radians(tbl_restaurant.longitude)\n" +
            "  -radians(:y))+sin(radians(:x))*sin(radians(tbl_restaurant.latitude)))) <=3\n" +
            "  ORDER BY (6371*acos(cos(radians(:x))*cos(radians(tbl_restaurant.latitude))*cos(radians(tbl_restaurant.longitude)\n" +
            "  -radians(:y))+sin(radians(:x))*sin(radians(tbl_restaurant.latitude))));", nativeQuery = true)
    fun findAllRestaurant(@Param(value = "x") x: Double, @Param(value = "y") y:Double) :List<Restaurant>
}
