package me.kzv.legacyboard.board.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import me.kzv.legacyboard.member.Member
import org.springframework.security.crypto.password.PasswordEncoder

data class SignupRequestDto(
    @field:NotBlank(message = "필수 정보입니다.")
    @field:Email(message = "이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "필수 정보입니다.")
    @field:Size(min=2, max=10, message = "닉네임은 2~10자로 입력하세요.")
    val nickname: String,

    @field:NotBlank(message = "필수 정보입니다.")
    @field:Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "8~16자 영문 대 소문자, 숫자를 사용하세요.")
    val password: String,
){
    fun toEntity(passwordEncoder: PasswordEncoder): Member = Member.createMember(email, nickname, password, passwordEncoder)
}
