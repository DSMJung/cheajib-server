package com.cheajib.cheajibserver.domain.owner.service

import com.cheajib.cheajibserver.domain.owner.domain.Owner
import com.cheajib.cheajibserver.domain.owner.domain.repository.OwnerRepository
import com.cheajib.cheajibserver.domain.owner.exception.OwnerNotFoundException
import com.cheajib.cheajibserver.domain.owner.presentation.dto.request.ApproveOwnerRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApproveOwnerService(
    private val ownerRepository: OwnerRepository
) {
    @Transactional
    fun execute(request: ApproveOwnerRequest) {
        val owner: Owner = ownerRepository.findByIdOrNull(request.userId) ?: throw OwnerNotFoundException.EXCEPTION
        owner.approve()
    }
}
