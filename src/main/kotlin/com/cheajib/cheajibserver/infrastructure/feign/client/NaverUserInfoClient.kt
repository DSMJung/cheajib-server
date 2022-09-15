package com.cheajib.cheajibserver.infrastructure.feign.client

import com.cheajib.cheajibserver.infrastructure.feign.dto.response.UserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "NaverInfoClient", url = "https://openapi.naver.com/v1/nid/me")
interface NaverUserInfoClient {

    @GetMapping
    fun getUserInfo(@RequestHeader("Authorization") authorization: String): UserInfoResponse
}