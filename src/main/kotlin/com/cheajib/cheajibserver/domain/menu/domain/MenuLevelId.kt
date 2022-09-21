package com.cheajib.cheajibserver.domain.menu.domain

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class MenuLevelId(
    @field:Column(columnDefinition = "BINARY(16)")
    val id: UUID,

    @field:Enumerated(EnumType.STRING)
    var level: Level
) : Serializable
