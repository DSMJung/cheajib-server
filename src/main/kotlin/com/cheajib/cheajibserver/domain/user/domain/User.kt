package com.cheajib.cheajibserver.domain.user.domain

import com.cheajib.cheajibserver.domain.user.domain.type.Sex
import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.validator.constraints.Length
import org.slf4j.event.Level
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@DynamicInsert
@Table(name = "tbl_user")
class User(

    override val id: UUID,

    @field:NotNull
    @field:Length(max = 64)
    val email: String,

    @field:NotNull
    @field:Length(max = 74)
    val name: String,

    @field:NotNull
    @field:Length(max = 60)
    val password: String,

    level: Level,

    @Column(nullable = false)
    @ColumnDefault("'default_image'")
    @field:Length(max = 255)
    val profile: String,

    sex: Sex

) : BaseUUIDEntity() {

    @field:NotNull
    @field:Length(max = 11)
    @Enumerated(EnumType.STRING)
    var level = level
        protected set

    @field:NotNull
    @field:Length(max = 1)
    @Enumerated(EnumType.STRING)
    var sex = sex
        protected set
}
