package me.kzv.legacyboard.reply.dto

import jakarta.validation.constraints.NotBlank

data class DeleteReplyRequestDto (
    @field:NotBlank val replyId: Long,
    @field:NotBlank val boardId: Long
)
