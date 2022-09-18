package com.cheajib.cheajibserver.domain.restaurant.domain

import com.cheajib.cheajibserver.global.entity.BaseTimeEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.validator.constraints.Length
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@DynamicInsert
@Table(name = "tbl_restaurant")
class Restaurant(

    override val id: UUID,

    @field:NotNull
    @field:Length(max = 100)
    val name: String,

    @field:NotNull
    @field:Length(max = 94)
    val address: String,

    @field: NotNull
    val latitude: Double,

    @field: NotNull
    val longitude: Double,

    @field:NotNull
    @ColumnDefault("'default_image'")
    @field:Length(max = 255)
    val imageUrl: String,

    isVerify: Boolean

) : BaseTimeEntity() {
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("'0'")
    var isVerify = isVerify
        protected set
}
