package com.cheajib.cheajibserver.domain.menu.facade

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import org.springframework.stereotype.Component

@Component
class MenuLevelFacade(
    private val menuRepository: MenuRepository,
    private val menuLevelRepository: MenuLevelRepository
) {

    fun getMenuLevel(menu: Menu): MenuLevel {
/*
        return menuLevelRepository.findTop1ByMenu(menu) ?: throw MenuNotFoundException.EXCEPTION
*/
        return menuLevelRepository.findFirstByMenu(menu) ?: throw MenuNotFoundException.EXCEPTION
    }
}