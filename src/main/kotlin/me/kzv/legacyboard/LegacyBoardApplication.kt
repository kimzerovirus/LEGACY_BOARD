package me.kzv.legacyboard

import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.entity.enums.RoleType
import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.crypto.password.PasswordEncoder

@EnableJpaAuditing
@SpringBootApplication
class LegacyBoardApplication(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {
    override fun run(vararg args: String) {
        memberRepository.save(
            Member.createMember(
                email = "test@test.com",nickname = "테스터1",
                password = "test1234", passwordEncoder = passwordEncoder
            ).apply { this.role = RoleType.ADMIN }
        )
    }
}

fun main(args: Array<String>) {
    runApplication<LegacyBoardApplication>(*args)
}
