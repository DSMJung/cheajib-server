package com.cheajib.cheajibserver.infrastructure.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "auth.google")
@ConstructorBinding
data class GoogleFeignProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUrl: String
)
