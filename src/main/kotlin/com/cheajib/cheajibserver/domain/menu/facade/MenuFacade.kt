package com.cheajib.cheajibserver.domain.menu.facade

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.exception.MenuNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MenuFacade(
    private val menuRepository: MenuRepository
) {
    fun getMenuById(id: UUID): Menu {
        return menuRepository.findByIdOrNull(id) ?: throw MenuNotFoundException.EXCEPTION
    }
}
