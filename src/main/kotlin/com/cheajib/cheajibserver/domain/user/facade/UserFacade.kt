package com.cheajib.cheajibserver.domain.user.facade

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import com.cheajib.cheajibserver.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun findUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw RuntimeException() // 대현이 pr 머지후 변경
    }

    fun findCurrentUser(): User {
        val authentication =
            SecurityContextHolder.getContext().authentication ?: throw RuntimeException() //대현이 pr머시 후 변경
        val email = authentication.name
        return findUserByEmail(email)
        
    private var userRepository: UserRepository
) {

    fun findByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }
}
