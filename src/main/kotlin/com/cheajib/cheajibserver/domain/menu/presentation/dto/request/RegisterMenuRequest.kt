package com.cheajib.cheajibserver.domain.menu.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class RegisterMenuRequest {

    @field: Size(max = 74)
    @field: NotBlank
    lateinit var name: String

    @field: Size(max = 500001)
    @field: NotBlank
    lateinit var price: String

    @field: NotBlank
    lateinit var menuImageUrl: String

    @field: Size(max = 1000)
    @field: NotBlank
    lateinit var description: String
}
