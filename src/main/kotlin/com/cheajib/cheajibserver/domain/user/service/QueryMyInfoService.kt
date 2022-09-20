package com.cheajib.cheajibserver.domain.user.service

import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import com.cheajib.cheajibserver.domain.user.presentation.dto.response.QueryMyInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyInfoService(
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): QueryMyInfoResponse {
        val user = userFacade.getCurrentUser()

        return QueryMyInfoResponse(
            name = user.name,
            email = user.email
        )
    }
}
