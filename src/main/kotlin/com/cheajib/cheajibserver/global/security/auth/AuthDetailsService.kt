package com.cheajib.cheajibserver.global.security.auth

import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userFacade.getUserByEmail(username)
        return AuthDetails(user = user)
    }
}
