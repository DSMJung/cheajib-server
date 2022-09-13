package com.cheajib.cheajibserver.domain.user.domain.repository

import com.cheajib.cheajibserver.domain.user.domain.Owner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OwnerRepository : CrudRepository<Owner, UUID>
