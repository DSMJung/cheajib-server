package com.cheajib.cheajibserver.domain.owner.service

import com.cheajib.cheajibserver.domain.owner.presentation.dto.request.VerifyOwnerRequest
import com.cheajib.cheajibserver.domain.owner.domain.Owner
import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.owner.domain.repository.OwnerRepository
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service

@Service
class VerifyOwnerService(
    private val userFacade: UserFacade,
    private val ownerRepository: OwnerRepository
) {

    fun execute(request: VerifyOwnerRequest) {
        val user: User = userFacade.getCurrentUser()

        val owner = Owner(
            id = user.id,
            postalCode = request.postalCode,
            address = request.address,
            detailAddress = request.detailAddress,
            ownerName = request.ownerName,
            birthDate = request.birthDate,
            phoneNumber = request.phoneNumber,
            idCard = request.idCard,
            businessCard = request.businessCard,
            isVerify = false
        )
        ownerRepository.save(owner)
    }
}
