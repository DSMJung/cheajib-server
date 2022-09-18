package com.cheajib.cheajibserver.domain.review.domain

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
@Table(name = "tbl_comment")
class Comment(

    override val id: UUID,

    @field:NotNull
    @field:Length(max = 500)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    val review: Review

) : BaseUUIDEntity()