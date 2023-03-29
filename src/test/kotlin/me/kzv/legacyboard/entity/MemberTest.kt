package me.kzv.legacyboard.entity

import me.kzv.legacyboard.member.Member
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class MemberTest{

}

fun createMember(
    email: String = "test1@test.com",
    nickname: String = "test",
    password: String = "test1234",
): Member {
    return Member.createMember(
        email = email, nickname = nickname, password = password,
        passwordEncoder = BCryptPasswordEncoder()
    )
}
