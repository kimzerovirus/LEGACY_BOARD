package me.kzv.legacyboard.board

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long>, BoardCustomRepository {
    @EntityGraph(attributePaths = ["member", "replyList", "tags"], type = EntityGraph.EntityGraphType.LOAD)
    fun findWithMemberAndReplyById(id: Long): Board?
}