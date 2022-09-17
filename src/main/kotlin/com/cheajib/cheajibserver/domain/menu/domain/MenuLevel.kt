package com.cheajib.cheajibserver.domain.menu.domain

import com.cheajib.cheajibserver.domain.user.domain.type.Level
import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_menu_level")
class MenuLevel(
    @Id
    val id: UUID,

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu,

    level: Level,

    @field:NotNull
    val levelCount: Int

) {
    @field:NotNull
    @ColumnDefault("'0'")
    @Enumerated(EnumType.STRING)
    var level = level
        protected set
}