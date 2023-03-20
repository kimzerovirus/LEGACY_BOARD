package me.kzv.legacyboard.controller.dtos

data class EditBoardRequestDto (
    val boardId: Long,
    val title: String,
    val content: String,
    val memberId: Long
)