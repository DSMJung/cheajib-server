package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.menu.domain.Menu
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class WriteReviewService(
    private val reviewRepository: ReviewRepository,
    private val menuRepository: MenuRepository,
    private val userFacade: UserFacade,
    private val menuFacade: MenuFacade
) {
    @Transactional
    fun execute(request: WriteReviewRequest) {
        val user = userFacade.findCurrentUser()

        for (menuElement in request.menuList) {
            val menu = menuFacade.findMenuById(menuElement.menuId)
            val newMenu = Menu(
                id = menu.id,
                name = menu.name,
                price = menu.price,
                description = menu.description,
                menuImageUrl = menu.menuImageUrl,
                level = menuElement.level,
                restaurant = menu.restaurant
            )
            menuRepository.save(newMenu)
        }

        val review = Review(
            id = UUID(0, 0),
            reviewPoint = request.reviewPoint,
            content = request.content,
            user = user
        )
        reviewRepository.save(review)
    }
}
