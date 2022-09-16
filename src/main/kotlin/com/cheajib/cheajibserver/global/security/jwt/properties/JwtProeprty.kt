package com.xquare.qkfflgkwk.global.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JwtProperty(
    var secretKey: String,
    val accessExp: Long,
    val refreshExp: Long,
    val header: String,
    val prefix: String
) {
    init {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }
}