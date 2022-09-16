package com.cheajib.cheajibserver.domain.user.facade

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw RuntimeException() // 대현이 pr 머지후 변경
    }

    fun getCurrentUser(): User {
        val authentication =
            SecurityContextHolder.getContext().authentication ?: throw RuntimeException() //대현이 pr머시 후 변경
        val email = authentication.name
        return getUserByEmail(email)
    }
}
