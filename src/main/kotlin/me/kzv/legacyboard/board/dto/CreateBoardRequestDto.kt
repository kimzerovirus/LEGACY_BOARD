package me.kzv.legacyboard.board.dto

import jakarta.validation.constraints.NotBlank
import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.member.Member

data class CreateBoardRequestDto(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
){
    fun toEntity(member: Member) = Board(member, title = title, content = content)
}

