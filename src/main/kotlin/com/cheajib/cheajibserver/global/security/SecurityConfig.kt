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

            // users
            .antMatchers(HttpMethod.PATCH, "/users/level").authenticated()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .antMatchers(HttpMethod.POST, "/users/token").permitAll()
            .antMatchers(HttpMethod.GET, "/naver/redirect").permitAll()
            .antMatchers(HttpMethod.GET, "/users/level").authenticated()
            .antMatchers(HttpMethod.GET, "/users").authenticated()

            // restaurants
            .antMatchers(HttpMethod.GET, "/restaurant/{restaurant-id}").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/details/{restaurant-id}").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/menu").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/reviews/{restaurant-id}").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/lists/map").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/lists").authenticated()
            .antMatchers(HttpMethod.GET, "/restaurant/info/{restaurant-id}").authenticated()


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
            .antMatchers(HttpMethod.GET, "/owner/{owner-id}").authenticated()

            // comments
            .antMatchers(HttpMethod.POST, "/comments").authenticated()

            .anyRequest().authenticated()
            .and().apply(FilterConfig(jwtTokenResolver, jwtTokenProvider, objectMapper))

        return http.build()
    }
}
