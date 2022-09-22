package com.cheajib.cheajibserver.domain.comment.domain

import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.global.entity.BaseTimeEntity
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_comment")
class Comment(

    override val id: UUID,

    override val createAt: LocalDateTime,

    @field:NotNull
    @field:Length(max = 500)
    val comment: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    val review: Review

) : BaseTimeEntity()
