package me.kzv.legacyboard.controller.dtos

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.exception.TisException

data class EditBoardRequestDto (
    val id: Long,
    val title: String,
    val content: String,
    val memberId: Long
){
    fun toEntity(member: Member): Board = Board(member, title = title, content = content)
}