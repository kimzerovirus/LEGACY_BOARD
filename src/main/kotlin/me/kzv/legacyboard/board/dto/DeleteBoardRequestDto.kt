package me.kzv.legacyboard.board.dto

import org.jetbrains.annotations.NotNull

data class DeleteBoardRequestDto(
    @field:NotNull val boardId: Long,
    @field:NotNull val memberId: Long,
)
