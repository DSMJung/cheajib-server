package com.cheajib.cheajibserver.domain.user.domain

import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
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

    @field:NotNull
    @field:Length(max = 255)
    val idCard: String,

    @field:NotNull
    @field:Length(max = 255)
    val businessCard: String,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("'0'")
    val isVerify: Boolean

) : BaseUUIDEntity()
