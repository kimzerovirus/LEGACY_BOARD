package me.kzv.legacyboard.controller.dtos

import jakarta.validation.constraints.NotBlank
import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.Member

data class CreateBoardRequestDto(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
){
    fun toEntity(member: Member) = Board(member, title = title, content = content)
}

data class CreateBoardResponseDto(
    val id: Long
)
