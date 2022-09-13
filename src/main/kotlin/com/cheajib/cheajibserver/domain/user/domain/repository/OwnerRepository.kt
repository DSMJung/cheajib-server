package com.cheajib.cheajibserver.domain.user.domain.repository

import com.cheajib.cheajibserver.domain.user.domain.Owner
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OwnerRepository : CrudRepository<Owner, UUID>