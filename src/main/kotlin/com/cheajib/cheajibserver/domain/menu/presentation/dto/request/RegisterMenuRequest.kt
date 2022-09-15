package com.cheajib.cheajibserver.domain.menu.presentation.dto.request

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class RegisterMenuRequest (

    @field: Size(max = 74)
    @field: NotBlank
    var name: String,

    @field: Size(max = 500001)
    @field: NotBlank
    var price: String,

    @field: NotBlank
    var menuImageUrl: String,

    @field: Size(max = 1000)
    @field: NotBlank
    var description: String,

    var level: Level
)
