package com.cheajib.cheajibserver.domain.user.presentation.dto.request

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import javax.validation.constraints.NotNull

data class SetVeganLevelRequest(

    @field:NotNull
    var level: Level
)
