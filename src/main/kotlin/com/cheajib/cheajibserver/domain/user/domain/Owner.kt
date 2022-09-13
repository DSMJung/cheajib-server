package com.cheajib.cheajibserver.domain.user.domain

import com.cheajib.cheajibserver.domain.user.domain.type.Sex
import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import org.slf4j.event.Level
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_owner")
class Owner(

    override val id: UUID,

    override val email: String,

    override val name: String,

    override val password: String,

    override var level: Level,

    override val profile: String,

    override var sex: Sex,

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
    val id_card: String,

    @field:NotNull
    @field:Length(max = 255)
    val businessCard: String,

    isCertified: Boolean

) : User(id, email, name, password, level, profile, sex) {

    @Column(nullable = false)
    @ColumnDefault("'0'")
    var isCertified = isCertified
        protected set
}
