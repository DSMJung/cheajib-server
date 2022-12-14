package com.cheajib.cheajibserver.domain.owner.presentation

import com.cheajib.cheajibserver.domain.owner.presentation.dto.request.ApproveOwnerRequest
import com.cheajib.cheajibserver.domain.owner.presentation.dto.request.VerifyOwnerRequest
import com.cheajib.cheajibserver.domain.owner.presentation.dto.response.CheckOwnerResponse
import com.cheajib.cheajibserver.domain.owner.service.ApproveOwnerService
import com.cheajib.cheajibserver.domain.owner.service.CheckOwnerService
import com.cheajib.cheajibserver.domain.owner.service.VerifyOwnerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/owner")
@RestController
class OwnerController(
    private val verifyOwnerService: VerifyOwnerService,
    private val approveOwnerService: ApproveOwnerService,
    private val checkOwnerService: CheckOwnerService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun verifyOwner(
        @RequestBody
        @Valid
        request: VerifyOwnerRequest
    ) {
        verifyOwnerService.execute(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    fun approveOwner(
        @RequestBody
        @Valid
        request: ApproveOwnerRequest
    ) {
        approveOwnerService.execute(request)
    }

    @GetMapping("/{owner-id}")
    fun checkOwner(
        @PathVariable("owner-id")
        ownerId: UUID
    ): CheckOwnerResponse {
        return checkOwnerService.execute(ownerId)
    }
}
