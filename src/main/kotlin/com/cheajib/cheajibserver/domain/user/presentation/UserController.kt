package com.cheajib.cheajibserver.domain.user.presentation

import com.cheajib.cheajibserver.domain.user.presentation.dto.SetVeganLevelRequest
import com.cheajib.cheajibserver.domain.user.service.SetVeganLevelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

@RequestMapping("/users")
class UserController(
    private val setVeganLevelService: SetVeganLevelService
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/level")
    fun setVeganLevel(
        @RequestBody
        @Valid
        request: SetVeganLevelRequest
    ) {
        return setVeganLevelService.execute(request)
    }
}
