package me.kzv.legacyboard.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import me.kzv.legacyboard.entity.enums.RoleType
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
class Member(
    /** 이메일 */
    @Column(nullable = false, unique = true)
    val email: String,

    /** 닉네임 */
    @Column(nullable = false, unique = true)
    val nickname: String,

    /** 비밀번호 */
    @Column(nullable = false)
    var password: String,

    /** 권한 */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: RoleType = RoleType.USER
) : BaseEntity() {
    companion object{
        fun createMember(
            email: String, nickname: String,
            password: String, passwordEncoder: PasswordEncoder
        ): Member {
            return Member(email, nickname, passwordEncoder.encode(password))
        }
    }
}