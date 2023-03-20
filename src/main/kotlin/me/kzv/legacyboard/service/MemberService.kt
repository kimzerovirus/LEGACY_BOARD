package me.kzv.legacyboard.service

import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun createMember(member: Member) {
        memberRepository.save(member)
    }

    @Transactional
    fun update(member: Member) {
        memberRepository.save(member)
    }

    @Transactional
    fun deleteMember(member: Member) {
        memberRepository.delete(member)
    }
}