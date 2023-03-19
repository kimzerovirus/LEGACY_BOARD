package me.kzv.legacyboard.service

import me.kzv.legacyboard.controller.dtos.SignupRequest
import me.kzv.legacyboard.controller.dtos.toEntity
import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun createMember(dto: SignupRequest) {
        memberRepository.save(dto.toEntity(passwordEncoder))
    }
}