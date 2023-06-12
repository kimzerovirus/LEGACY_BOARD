package me.kzv.legacyboard.tag

import jakarta.persistence.*
import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.infra.common.jpa.BaseEntity

@Entity
class BoardTag(
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    val tag: Tag,
) : BaseEntity()
