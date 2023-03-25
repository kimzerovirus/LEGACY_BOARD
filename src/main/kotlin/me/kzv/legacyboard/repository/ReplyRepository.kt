package me.kzv.legacyboard.repository

import me.kzv.legacyboard.entity.Reply
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, Long> {
    @EntityGraph(attributePaths = ["member", "board"], type = EntityGraph.EntityGraphType.LOAD)
    fun findByBoard_id(boardId: Long): List<Reply>
}