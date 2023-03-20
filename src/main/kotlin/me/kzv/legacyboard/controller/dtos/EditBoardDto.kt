package me.kzv.legacyboard.controller.dtos

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.exception.TisException

data class EditBoardRequest (
    val id: Long,
    val title: String,
    val content: String,
    val memberId: Long
){
    fun validateWriter(member:Member) {
        if(this.memberId != member.id) throw TisException("로그인한 회원과 글 작성자가 다름!!!")
    }

    fun toEntity(member: Member): Board = Board(member, title = title, content = content)
}