package com.cheajib.cheajibserver.domain.menu.domain

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_menu_level")
class MenuLevel(

    @EmbeddedId
    val id: MenuLevelId,

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu,

    levelCount: Int

) : Serializable {
    @field:NotNull
    var levelCount = levelCount
        protected set

    fun plusLevelCount() {
        this.levelCount += 1
    }
}
