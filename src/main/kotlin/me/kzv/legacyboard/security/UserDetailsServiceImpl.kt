package me.kzv.legacyboard.security

import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val memberRepository: MemberRepository
):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByEmail(username) ?: throw UsernameNotFoundException("$username 회원이 없습니다.")
        return UserDetailsImpl(member)
    }
}