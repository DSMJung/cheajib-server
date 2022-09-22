package com.cheajib.cheajibserver.domain.review.presentation.dto.request

import com.cheajib.cheajibserver.domain.review.presentation.dto.response.MenuInfoElement
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class WriteReviewRequest(

    @field:NotNull
    val reviewPoint: Int,

    @field:Size(max = 300)
    @field:NotBlank
    val content: String,

    val imageUrl: List<String>,

    val menuList: List<MenuInfoElement>
)
