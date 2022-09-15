package com.cheajib.cheajibserver.infrastructure.feign.client

import com.cheajib.cheajibserver.infrastructure.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "naverFeignClient", url = "https://nid.naver.com/oauth2.0/token")
interface NaverTokenClient {

    @PostMapping
    fun getCode(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam code: String,
        @RequestParam state: String
    ): TokenResponse
}
