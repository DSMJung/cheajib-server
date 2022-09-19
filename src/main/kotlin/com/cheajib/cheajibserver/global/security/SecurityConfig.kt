package com.cheajib.cheajibserver.global.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsUtils

@EnableWebSecurity
class SecurityConfig {
    @Throws(Exception::class)
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .formLogin().disable()
            .cors()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            // users
            .antMatchers(HttpMethod.PATCH, "/users/level").authenticated()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .antMatchers(HttpMethod.POST, "/users/token").permitAll()
            .antMatchers(HttpMethod.GET, "/naver/redirect").permitAll()
            // restaurants
            .antMatchers(HttpMethod.GET, "/restaurant/{restaurant-id}").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/details/{restaurant-id}").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/menu").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/reviews").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/lists/map").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/lists").authenticated()
            // menu
            .antMatchers(HttpMethod.POST, "/menu").authenticated()
            .antMatchers(HttpMethod.DELETE, "/menu/{menu-id}").authenticated()
            // images
            .antMatchers(HttpMethod.POST, "/images").permitAll()
            // review
            .antMatchers(HttpMethod.DELETE, "/review").authenticated()
            .antMatchers(HttpMethod.GET, "/review/my-review/lists").authenticated()
            .antMatchers(HttpMethod.POST, "/review").authenticated()
            // owner
            .antMatchers(HttpMethod.POST, "/owner").authenticated()
            .antMatchers(HttpMethod.PATCH, "/owner").authenticated()
            // comments
            .antMatchers(HttpMethod.POST, "/comments").authenticated()

        return http.build()
    }
}
