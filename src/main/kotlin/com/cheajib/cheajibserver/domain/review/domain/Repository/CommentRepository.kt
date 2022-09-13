package com.cheajib.cheajibserver.domain.review.domain.Repository

import com.cheajib.cheajibserver.domain.review.domain.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentRepository : CrudRepository<Comment, UUID>
