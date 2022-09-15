package com.cheajib.cheajibserver.infrastructure.feign.dto.response

data class NaverUserInfoElement(
    val email: String,
    val name: String,
    val profile_image: String,
    val gender: String?
)
