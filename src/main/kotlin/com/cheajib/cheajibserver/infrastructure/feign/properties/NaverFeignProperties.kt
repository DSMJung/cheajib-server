package com.cheajib.cheajibserver.infrastructure.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "auth.naver")
@ConstructorBinding
data class NaverFeignProperties(
    val clientId: String,
    val clientSecret: String,
    val state: String
)
