package me.kzv.legacyboard.tag

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TagServiceTest {
    @Autowired
    lateinit var tagService: TagService

    @Test
    fun `pk값이 없는 태그는 새로 생성하는 태그이며 db에 저장되고 pk를 부여받는다`() {
        val tags = mutableListOf(Tag("태그1"), Tag("태그2"))
        for (tag in tags) {
            assertThat(tag.id).isNull()
        }

        val newTags = tagService.searchAndCreate(tags)
        for ((index, tag) in newTags.withIndex()) {
            assertThat(tag.id).isEqualTo((index + 1).toLong())
        }
    }
}