package me.kzv.legacyboard.controller.dtos

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.Member

data class CreateBoardRequest(
    val title: String,
    val content: String,
){
    fun toEntity(member: Member) = Board(member, title = title, content = content)
}
