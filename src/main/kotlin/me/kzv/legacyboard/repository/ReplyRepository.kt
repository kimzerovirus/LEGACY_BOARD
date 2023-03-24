package me.kzv.legacyboard.repository

import me.kzv.legacyboard.entity.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, Long> {
    fun findByBoard_id(boardId: Long): List<Reply>
}