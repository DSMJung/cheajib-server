package com.cheajib.cheajibserver.global.security

import com.cheajib.cheajibserver.global.security.jwt.JwtTokenProvider
import com.cheajib.cheajibserver.global.security.jwt.JwtTokenResolver
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenResolver: JwtTokenResolver,
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) {
    @Throws(Exception::class)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors()

        http
            .authorizeRequests()

            .antMatchers(HttpMethod.GET, "/auth").permitAll()

            .anyRequest().authenticated()
            .and().apply(FilterConfig(jwtTokenResolver, jwtTokenProvider, objectMapper))

        return http.build()
    }
}
