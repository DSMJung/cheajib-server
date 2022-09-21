package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuLevelRepository : CrudRepository<MenuLevel, UUID> {
    fun findAllByMenuIdOrderByLevelCount(menuId: UUID): List<MenuLevel>
    fun findByMenu(menu: Menu): MenuLevel
}
