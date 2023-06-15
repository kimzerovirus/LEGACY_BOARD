package me.kzv.legacyboard.board.dto

import jakarta.validation.constraints.NotBlank
import me.kzv.legacyboard.tag.Tag
import org.jetbrains.annotations.NotNull

data class EditBoardRequestDto (
    @field:NotBlank val title: String,
    @field:NotBlank val content: String,
    @field:NotNull val memberId: Long,
    val tags: List<Tag>
)