package com.cheajib.cheajibserver.domain.review.domain

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.global.entity.BaseUUIDEntity
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_review")
class Review(

    override val id: UUID,

    reviewPoint: Int,

    @field:NotNull
    @field:Length(max = 300)
    val content: String,

    @field:NotNull
    val createdAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu

) : BaseUUIDEntity() {

    @field:NotNull
    @field:Length(max = 5)
    var reviewPoint = reviewPoint
        protected set
}
