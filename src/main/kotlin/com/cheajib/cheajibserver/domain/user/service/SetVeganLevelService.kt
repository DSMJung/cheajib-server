package com.cheajib.cheajibserver.domain.user.service

import com.cheajib.cheajibserver.domain.user.presentation.dto.SetVeganLevelRequest
import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SetVeganLevelService(
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(setVeganLevelRequest: SetVeganLevelRequest) {
        val user: User = userFacade.getCurrentUser()
        user.setVegan(setVeganLevelRequest.level)
    }
}
