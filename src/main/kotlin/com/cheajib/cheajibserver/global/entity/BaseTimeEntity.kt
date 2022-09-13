package com.cheajib.cheajibserver.global.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTimeEntity : BaseUUIDEntity() {

    @CreatedDate
    val creatAt: LocalDateTime = LocalDateTime.now()
}
