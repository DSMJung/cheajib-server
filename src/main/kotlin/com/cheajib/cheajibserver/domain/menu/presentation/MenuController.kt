package com.cheajib.cheajibserver.domain.menu.presentation

import com.cheajib.cheajibserver.domain.menu.presentation.dto.request.RegisterMenuRequest
import com.cheajib.cheajibserver.domain.menu.service.RegisterMenuService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RequestMapping("/menu")
@RestController
class MenuController(
    private val registerMenuService: RegisterMenuService
) {
    @PostMapping("/{restaurant-id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerMenu(
        @PathVariable("restaurant-id") restaurantId: UUID,
        @Valid @RequestBody request: RegisterMenuRequest
    ) {
        registerMenuService.execute(restaurantId, request)
    }
}
