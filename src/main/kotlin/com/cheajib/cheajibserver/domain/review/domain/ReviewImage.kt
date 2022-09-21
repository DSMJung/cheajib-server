package com.cheajib.cheajibserver.domain.review.domain

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_review_image")
class ReviewImage(

    @EmbeddedId
    val id: ReviewImageId,

    @field:NotNull
    val imageUrl: String,

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    val review: Review

) : Serializable
