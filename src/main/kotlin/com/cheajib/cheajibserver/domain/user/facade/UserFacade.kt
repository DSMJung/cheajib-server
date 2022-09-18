package com.cheajib.cheajibserver.domain.user.facade

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import com.cheajib.cheajibserver.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }

    fun getCurrentUser(): User {
        val authentication =
            SecurityContextHolder.getContext().authentication ?: throw UserNotFoundException.EXCEPTION
        val email = authentication.name
        return getUserByEmail(email)
    }
}
