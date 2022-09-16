package com.cheajib.cheajibserver.domain.comment.domain.repository

import com.cheajib.cheajibserver.domain.comment.domain.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentRepository : CrudRepository<Comment, UUID>
