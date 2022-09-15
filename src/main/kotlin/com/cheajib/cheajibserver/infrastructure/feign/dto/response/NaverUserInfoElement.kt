package com.cheajib.cheajibserver.infrastructure.feign.dto.response

data class NaverUserInfoElement(
    val email: String,
    val name: String,
    val profileImage: String,
    val gender: String?
)
