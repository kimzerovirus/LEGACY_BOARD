package me.kzv.legacyboard.repository

import me.kzv.legacyboard.infra.test.MyBatisUser
import me.kzv.legacyboard.infra.test.MyBatisUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MyBatisUserRepositoryTest {
    @Autowired lateinit var repository: MyBatisUserRepository

    @Test
    fun `마이바티스 테스트`() {
        //given
        val user = createMyBatisUser()

        //when
        val result = repository.insertUser(user)

        //then
        assertThat(result).isEqualTo(1)
    }
}

fun createMyBatisUser(
    username: String = "oh",
    password: String = "1234",
    email: String = "oh@email.com"
): MyBatisUser {
    return MyBatisUser(username = username, password = password, email = email)
}