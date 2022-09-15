package com.cheajib.cheajibserver.domain.user.domain.repository

import com.cheajib.cheajibserver.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun findByEmail(email: String): User?
}
