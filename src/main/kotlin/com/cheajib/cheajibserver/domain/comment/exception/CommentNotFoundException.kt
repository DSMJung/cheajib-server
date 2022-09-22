package com.cheajib.cheajibserver.domain.comment.exception

import com.cheajib.cheajibserver.global.error.GlobalException
import com.cheajib.cheajibserver.global.error.properties.GlobalErrorCode

object CommentNotFoundException : GlobalException(GlobalErrorCode.COMMENT_NOT_FOUND) {
    val EXCEPTION = CommentNotFoundException
}