package com.cheajib.cheajibserver.domain.owner.service

import com.cheajib.cheajibserver.domain.owner.domain.repository.OwnerRepository
import com.cheajib.cheajibserver.domain.owner.presentation.dto.response.CheckOwnerResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CheckOwnerService(
    private val ownerRepository: OwnerRepository
) {
    @Transactional(readOnly = true)
    fun execute(userId: UUID): CheckOwnerResponse {
        val isOwner = ownerRepository.existsById(userId)

        return CheckOwnerResponse(
            isOwner = isOwner
        )
    }
}
