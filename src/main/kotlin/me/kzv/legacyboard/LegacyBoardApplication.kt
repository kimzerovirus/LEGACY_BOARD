package me.kzv.legacyboard

import me.kzv.legacyboard.entity.Member
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
        memberRepository.save(Member.createMember("test@test.com", "테스터1", "test1234", passwordEncoder))
    }
}

fun main(args: Array<String>) {
    runApplication<LegacyBoardApplication>(*args)
}
