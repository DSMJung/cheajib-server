package com.cheajib.cheajibserver.domain.menu.domain

import com.cheajib.cheajibserver.domain.restaurant.domain.Restaurant
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import org.hibernate.validator.constraints.Length
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
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
    @Length(max = 74)
    val name: String,

    price: String,

    @field:NotNull
    @Length(max = 1000)
    val description: String,

    menuImageUrl: String,

    level: Level,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    val restaurant: Restaurant

) : BaseUUIDEntity() {

    @field:NotNull
    @Length(max = 500001)
    var price = price
        protected set

    @field:NotNull
    var menuImageUrl = menuImageUrl
        protected set

    @field:NotNull
    @field:Length(max = 11)
    @Enumerated(EnumType.STRING)
    var level = level
        protected set
}
