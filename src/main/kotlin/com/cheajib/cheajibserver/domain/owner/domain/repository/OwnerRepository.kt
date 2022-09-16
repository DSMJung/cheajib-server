package com.cheajib.cheajibserver.domain.owner.domain.repository

import com.cheajib.cheajibserver.domain.owner.domain.Owner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OwnerRepository : CrudRepository<Owner, UUID>
