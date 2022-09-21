package com.cheajib.cheajibserver.domain.user.presentation

import com.cheajib.cheajibserver.domain.user.service.NaverOauthService
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.TokenResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OauthController(
    private val naverOauthService: NaverOauthService
) {
    @GetMapping("/auth")
    fun getNaverCode(@RequestParam code: String): TokenResponse {
        return naverOauthService.getCode(code)
    }
}
