package com.cheajib.cheajibserver.domain.review.presentation.dto.request

import com.cheajib.cheajibserver.domain.review.presentation.dto.response.MenuInfoElement
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class WriteReviewRequest(

    var reviewPoint: Int,

    @field: Size(max = 300)
    @field: NotBlank
    var content: String,

    @field: NotBlank
    var imageUrl: List<String>,

    var menuList: List<MenuInfoElement>
)
