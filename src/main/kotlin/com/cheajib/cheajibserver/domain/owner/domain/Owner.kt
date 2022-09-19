package com.cheajib.cheajibserver.domain.owner.domain

import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import com.cheajib.cheajibserver.infrastructure.aws.defaultImage.DefaultImage
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.validator.constraints.Length
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@DynamicInsert
@Table(name = "tbl_owner")
class Owner(

    override val id: UUID,

    @field:NotNull
    @field:Length(max = 5)
    val postalCode: String,

    @field:NotNull
    @field:Length(max = 94)
    val address: String,

    @field:NotNull
    @field:Length(max = 94)
    val detailAddress: String,

    @field:NotNull
    @field:Length(max = 74)
    val ownerName: String,

    @field:NotNull
    @field:Length(max = 8)
    val birthDate: String,

    @field:NotNull
    @field:Length(max = 11)
    val phoneNumber: String,

    idCard: String,

    businessCard: String,

    isVerify: Boolean

) : BaseUUIDEntity() {

    @field:NotNull
    @field:Length(max = 255)
    var businessCard = businessCard
        protected set

    @field:NotNull
    @field:Length(max = 255)
    var idCard = idCard
        protected set

    @field:NotNull
    @ColumnDefault("'0'")
    var isVerify = isVerify
        protected set

    fun approve() {
        this.isVerify = true
        this.idCard = DefaultImage.CARD_IMAGE
        this.businessCard = DefaultImage.CARD_IMAGE
    }
}
