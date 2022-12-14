package com.cheajib.cheajibserver.domain.menu.domain

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
import org.hibernate.annotations.ColumnDefault
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

    @field:NotNull
    @field:Length(max = 74)
    val name: String,

    price: String,

    @field:NotNull
    @field:Length(max = 1000)
    val description: String,

    menuImageUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    val restaurant: Restaurant

) : BaseUUIDEntity() {

    @field:NotNull
    @Length(max = 500001)
    var price = price
        protected set

    @ColumnDefault(DefaultImage.MENU_IMAGE)
    @field:Length(max = 255)
    var menuImageUrl = menuImageUrl
        protected set
}
