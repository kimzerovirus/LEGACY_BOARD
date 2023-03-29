package me.kzv.legacyboard.reply

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import me.kzv.legacyboard.infra.common.jpa.BaseEntity
import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.member.Member

@Entity
class Reply(
    /** 댓글 작성자 */
    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    /** 게시글 */
    @ManyToOne(fetch = FetchType.LAZY)
    val board: Board,

    /** 댓글 내용 */
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,
) : BaseEntity() {
    fun update(content: String) {
        this.content = content
    }
}