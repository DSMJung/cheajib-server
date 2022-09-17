package com.cheajib.cheajibserver.domain.menu.presentation

import com.cheajib.cheajibserver.domain.menu.presentation.dto.request.RegisterMenuRequest
import com.cheajib.cheajibserver.domain.menu.presentation.dto.response.MenuListResponse
import com.cheajib.cheajibserver.domain.menu.service.DeleteMenuService
import com.cheajib.cheajibserver.domain.menu.service.QueryMenuListService
import com.cheajib.cheajibserver.domain.menu.service.RegisterMenuService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RequestMapping("/menu")
@RestController
class MenuController(
    private val registerMenuService: RegisterMenuService,
    private val deleteMenuService: DeleteMenuService,
    private val queryMenuListService: QueryMenuListService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurant-id}")
    fun registerMenu(
        @PathVariable("restaurant-id")
        restaurantId: UUID,
        @Valid
        @RequestBody
        request: RegisterMenuRequest
    ) {
        registerMenuService.execute(restaurantId, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{menu-id}")
    fun deleteMenu(
        @PathVariable("menu-id")
        menuId: UUID
    ) {
        deleteMenuService.execute(menuId)
    }

    @GetMapping("/{restaurant-id}")
    fun queryMenu(
        @PathVariable("restaurant-id")
        restaurantId: UUID
    ): MenuListResponse {
        return queryMenuListService.execute(restaurantId)
    }
}
