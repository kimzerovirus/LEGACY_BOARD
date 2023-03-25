package me.kzv.legacyboard.controller.dtos

import jakarta.validation.constraints.NotBlank

data class EditReplyRequestDto (
    @field:NotBlank val replyId: Long,
    @field:NotBlank val boardId: Long,
    @field:NotBlank val content: String,
)
