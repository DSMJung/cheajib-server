package com.cheajib.cheajibserver.domain.user.domain.repository

import com.cheajib.cheajibserver.domain.user.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String>
