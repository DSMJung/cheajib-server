package com.cheajib.cheajibserver.infrastructure.feign.dto.request


data class NaverCodeRequest(
    val grant_type: String,
    val client_id: String,
    val client_secret: String,
    val code: String,
    val state: String
)