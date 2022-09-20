package com.cheajib.cheajibserver.domain.user.service

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import com.cheajib.cheajibserver.domain.user.presentation.dto.response.QueryVeganLevelResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryVeganLevelService(
    private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun execute(): QueryVeganLevelResponse {
        val user: User = userFacade.getCurrentUser()

        return QueryVeganLevelResponse(
            level = user.level
        )
    }
}
