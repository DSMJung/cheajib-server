package com.cheajib.cheajibserver.global.security

import com.cheajib.cheajibserver.global.error.ExceptionFilter
import com.cheajib.cheajibserver.global.security.jwt.JwtTokenFilter
import com.cheajib.cheajibserver.global.security.jwt.JwtTokenProvider
import com.cheajib.cheajibserver.global.security.jwt.JwtTokenResolver
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtTokenResolver: JwtTokenResolver,
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            JwtTokenFilter(jwtTokenResolver, jwtTokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
        builder.addFilterBefore(
            ExceptionFilter(objectMapper), JwtTokenFilter::class.java
        )
    }
}