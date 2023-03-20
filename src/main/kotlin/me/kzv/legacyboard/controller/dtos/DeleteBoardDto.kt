package me.kzv.legacyboard.controller.dtos

data class DeleteBoardRequestDto(
    val boardId: Long,
    val memberId: Long,
)
