package com.cheajib.cheajibserver.domain.owner.presentation

import com.cheajib.cheajibserver.domain.owner.presentation.dto.request.VerifyOwnerRequest
import com.cheajib.cheajibserver.domain.owner.service.OwnerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/owner")
@RestController
class OwnerController(
    private val ownerService: OwnerService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun verifyOwner(
        @RequestBody
        @Valid
        request: VerifyOwnerRequest
    ) {
        ownerService.verifyOwner(request)
    }
}
