package me.kzv.legacyboard.reply.dto

import me.kzv.legacyboard.reply.Reply
import java.time.LocalDateTime

data class ReplyResponseDto(
    val replyId: Long,
    val boardId: Long,
    val content: String,
    val nickname: String,
    val isReplyer: Boolean,
    val date: LocalDateTime,
)

fun Reply.toResponseDto(currentMemberId: Long): ReplyResponseDto =
    ReplyResponseDto(
        replyId = id!!, boardId = board.id!!, content = content, nickname = member.nickname,
        isReplyer = member.id!! == currentMemberId, date = createdAt!!)