package me.kzv.legacyboard.member

import me.kzv.legacyboard.infra.exception.TisException
import me.kzv.legacyboard.member.dto.MemberActivityResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun createMember(member: Member) {
        memberRepository.findByEmail(member.email)?.let { throw TisException("이미 회원가입된 이메일입니다.", "email") }
        memberRepository.findByNickname(member.nickname)?.let { throw TisException("사용할 수 없는 닉네임입니다.", "nickname") }
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

    fun getMemberActivity(id: Long, pageable: Pageable): Page<MemberActivityResponseDto> {
        return memberRepository.getMemberActivity(id, pageable).map {
            MemberActivityResponseDto.of(it)
        }
    }
}