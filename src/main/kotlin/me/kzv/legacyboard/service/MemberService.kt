package me.kzv.legacyboard.service

import me.kzv.legacyboard.controller.dtos.SignupRequest
import me.kzv.legacyboard.controller.dtos.toEntity
import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun createMember(dto: SignupRequest) {
        memberRepository.save(dto.toEntity())
    }
}