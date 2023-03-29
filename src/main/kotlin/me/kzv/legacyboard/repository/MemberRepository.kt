package me.kzv.legacyboard.repository

import me.kzv.legacyboard.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun findByEmail(email: String): Member?
    fun findByNickname(nickname: String): Member?
}