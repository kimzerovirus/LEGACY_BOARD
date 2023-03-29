package me.kzv.legacyboard.member.validator

import me.kzv.legacyboard.board.dto.SignupRequestDto
import me.kzv.legacyboard.member.MemberRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class SignupRequestValidator(
    private val memberRepository: MemberRepository
): Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return clazz.isAssignableFrom(SignupRequestDto::class.java)
    }

    override fun validate(target: Any, errors: Errors) {
        val dto = target as SignupRequestDto

        if (memberRepository.existsByEmail(dto.email)) {
            errors.rejectValue("email", "invalid.email", arrayOf(dto.email), "이미 사용중인 이메일입니다.")
        }

        if (memberRepository.existsByNickname(dto.nickname)) {
            errors.rejectValue("nickname", "invalid.nickname", arrayOf(dto.email), "이미 사용중인 닉네임입니다.")
        }
    }
}