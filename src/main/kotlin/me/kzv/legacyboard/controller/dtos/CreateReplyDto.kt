package me.kzv.legacyboard.controller.dtos

import jakarta.validation.constraints.NotBlank

data class CreateReplyRequestDto(
    @field:NotBlank val content: String
)