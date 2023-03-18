package me.kzv.legacyboard.controller.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import me.kzv.legacyboard.entity.Member

data class SignupRequest(
    @field:NotBlank(message = "필수 정보입니다.")
    @field:Email(message = "이메일만 입력 가능합니다.")
    val email: String,

    @field:NotBlank(message = "필수 정보입니다.")
    val nickname: String,

    @field:NotBlank(message = "필수 정보입니다.")
    @field:Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "8~16자 영문 대 소문자, 숫자를 사용하세요.")
    val password: String,
)

fun SignupRequest.toEntity(): Member = Member(email, nickname, password)

