package com.cheajib.cheajibserver.domain.owner.presentation.dto.request

import com.cheajib.cheajibserver.domain.user.domain.type.Sex
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class VerifyOwnerRequest(
    @field:NotNull
    val sex: Sex,

    @field:NotBlank
    @field:Size(min = 5, max = 5)
    val postalCode: String,

    @field:NotBlank
    val address: String,

    @field:NotBlank
    val detailAddress: String,

    @field:NotBlank
    val ownerName: String,

    @field:NotBlank
    @field:Size(min = 8, max = 8)
    val birthDate: String,

    @field:NotBlank
    @field:Size(min = 11, max = 11)
    val phoneNumber: String,

    @field:NotBlank
    val isCard: String,

    @field:NotBlank
    val businessCard: String
)
