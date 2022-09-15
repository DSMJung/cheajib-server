package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.review.domain.Review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReviewRepository : CrudRepository<Review, UUID>