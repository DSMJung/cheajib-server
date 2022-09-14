package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuRepository : CrudRepository<Menu, UUID>
