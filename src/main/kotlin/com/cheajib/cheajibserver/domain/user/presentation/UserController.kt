package com.cheajib.cheajibserver.domain.user.presentation

import com.cheajib.cheajibserver.domain.user.presentation.dto.request.SetVeganLevelRequest
import com.cheajib.cheajibserver.domain.user.presentation.dto.response.QueryMyInfoResponse
import com.cheajib.cheajibserver.domain.user.presentation.dto.response.QueryVeganLevelResponse
import com.cheajib.cheajibserver.domain.user.service.QueryMyInfoService
import com.cheajib.cheajibserver.domain.user.service.QueryVeganLevelService
import com.cheajib.cheajibserver.domain.user.service.SetVeganLevelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val setVeganLevelService: SetVeganLevelService,
    private val queryVeganLevelService: QueryVeganLevelService,
    private val queryMyInfoService: QueryMyInfoService
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

    @GetMapping("/level")
    fun queryVeganLevel(): QueryVeganLevelResponse {
        return queryVeganLevelService.execute()
    }

    @GetMapping
    fun queryMyInfo(): QueryMyInfoResponse {
        return queryMyInfoService.execute()
    }
}
