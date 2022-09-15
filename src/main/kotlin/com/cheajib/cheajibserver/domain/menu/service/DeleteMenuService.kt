package com.cheajib.cheajibserver.domain.menu.service

import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeleteMenuService(
    private val menuFacade: MenuFacade,
    private val menuRepository: MenuRepository
) {

    @Transactional
    fun execute(menuId: UUID) {
        val menu = menuFacade.getMenuById(menuId)
        menuRepository.delete(menu)
    }
}
