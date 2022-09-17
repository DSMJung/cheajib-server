package com.cheajib.cheajibserver.domain.auth.domain.repository

import com.cheajib.cheajibserver.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String>
