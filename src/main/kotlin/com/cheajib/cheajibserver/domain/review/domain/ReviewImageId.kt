package com.cheajib.cheajibserver.domain.review.domain

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ReviewImageId(
    @field:Column(nullable = false)
    val sequence: Int,

    @field:Column(columnDefinition = "BINARY(16)")
    val id: UUID
) : Serializable
