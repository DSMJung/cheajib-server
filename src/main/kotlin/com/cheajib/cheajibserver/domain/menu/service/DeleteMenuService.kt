package com.cheajib.cheajibserver.domain.menu.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteMenuService(
    private val menuFacade: MenuFacade,
    private val menuRepository: MenuRepository
) {
    fun execute(menuId: UUID) {
        val menu = menuFacade.getMenuById(menuId)
        menuRepository.delete(menu)
    }
}
