package com.cheajib.cheajibserver.domain.user.facade

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import com.cheajib.cheajibserver.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private var userRepository: UserRepository
) {

    fun findByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }
}
