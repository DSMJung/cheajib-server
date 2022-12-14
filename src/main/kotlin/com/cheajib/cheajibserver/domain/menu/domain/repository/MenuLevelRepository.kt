package com.cheajib.cheajibserver.domain.menu.domain.repository

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevelId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuLevelRepository : CrudRepository<MenuLevel, UUID> {
    fun findAllByMenuIdOrderByLevelCount(menuId: UUID): List<MenuLevel>
    fun findById(id: MenuLevelId): MenuLevel?
    fun findFirstByMenuOrderByLevelCountDesc(menu: Menu): MenuLevel?
    fun findFirstByMenu(menu: Menu): MenuLevel?
}
