package com.cheajib.cheajibserver.domain.menu.domain

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import org.hibernate.validator.constraints.Length
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_menu")
class Menu(

    override val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    val restaurant: Restaurant,

    @field:NotNull
    @Length(max = 74)
    val name: String,

    price: Int,

    menuImageUrl: String

) : BaseUUIDEntity() {

    @field:NotNull
    @Length(max = 1000000)
    var price = price
        protected set

    @field:NotNull
    var menuImageUrl = menuImageUrl
        protected set
}
