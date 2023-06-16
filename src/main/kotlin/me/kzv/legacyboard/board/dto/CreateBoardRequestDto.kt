package me.kzv.legacyboard.board.dto

import jakarta.validation.constraints.NotBlank
import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.board.TopicType
import me.kzv.legacyboard.member.Member
import me.kzv.legacyboard.tag.Tag

data class CreateBoardRequestDto(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    @field:NotBlank
    val topic: TopicType,
    val tags: List<Tag>
){
    fun toEntity(member: Member) = Board(member, title = title, content = content, topic = topic)
}

