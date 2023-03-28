package me.kzv.legacyboard

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.entity.enums.RoleType
import me.kzv.legacyboard.repository.BoardRepository
import me.kzv.legacyboard.repository.MemberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class LegacyBoardApplication(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val boardRepository: BoardRepository,
) : CommandLineRunner {
    override fun run(vararg args: String) {
        memberRepository.save(
            Member.createMember(
                email = "admin@kimzerovirus.com", nickname = "어드민",
                password = "test1234", passwordEncoder = passwordEncoder
            ).apply { this.role = RoleType.ADMIN })
        val member = memberRepository.save(
            Member.createMember(
                email = "test@test.com",nickname = "테스터1",
                password = "test1234", passwordEncoder = passwordEncoder
            ).apply { this.role = RoleType.ADMIN })
        (1..12).forEach { boardRepository.save(Board(member, title = "test$it", content = "content test$it")) }
    }
}

fun main(args: Array<String>) {
    runApplication<LegacyBoardApplication>(*args)
}
