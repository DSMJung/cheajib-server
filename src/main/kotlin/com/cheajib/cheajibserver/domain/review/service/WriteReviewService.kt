package com.cheajib.cheajibserver.domain.review.service

import com.cheajib.cheajibserver.domain.menu.domain.MenuLevel
import com.cheajib.cheajibserver.domain.menu.domain.repository.MenuLevelRepository
import com.cheajib.cheajibserver.domain.menu.facade.MenuFacade
import com.cheajib.cheajibserver.domain.review.domain.Repository.ReviewRepository
import com.cheajib.cheajibserver.domain.review.domain.Review
import com.cheajib.cheajibserver.domain.review.presentation.dto.request.WriteReviewRequest
import com.cheajib.cheajibserver.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class WriteReviewService(
    private val reviewRepository: ReviewRepository,
    private val menuLevelRepository: MenuLevelRepository,
    private val userFacade: UserFacade,
    private val menuFacade: MenuFacade
) {
    @Transactional
    fun execute(request: WriteReviewRequest) {
        val user = userFacade.getCurrentUser()

        for (menuElement in request.menuList) {
            val menu = menuFacade.getMenuById(menuElement.menuId)

            val menuLevel = MenuLevel(
                id = menu.id,
                menu = menu,
                level = menuElement.level,
                levelCount = 0
            )
            menuLevelRepository.save(menuLevel)
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
