package me.kzv.legacyboard.tag

import me.kzv.legacyboard.board.BoardRepository
import me.kzv.legacyboard.board.createBoard
import me.kzv.legacyboard.member.MemberRepository
import me.kzv.legacyboard.member.createMember
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TagServiceTest {
    @Autowired lateinit var tagService: TagService
    @Autowired lateinit var tagRepository: TagRepository
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var boardRepository: BoardRepository
    @Autowired lateinit var boardTagRepository: BoardTagRepository

    @BeforeEach
    fun init(){
        val member = memberRepository.save(createMember())
        boardRepository.save(createBoard(member = member))
    }

    @Test
    fun `pk값이 없는 태그는 새로 생성하는 태그이며 db에 저장되고 pk를 부여받는다`() {
        val board = boardRepository.findAll().first()
        val tags = mutableListOf(Tag("태그1"), Tag("태그2"))
        for (tag in tags) assertThat(tag.id).isNull()

        tagService.searchAndCreate(board, tags)

        val size = tags.size
        val savedTags = tagRepository.findAll()
        for (tag in savedTags) assertThat(tag.id).isNotNull
        assertThat(savedTags.size).isEqualTo(size)

        val savedBoardTags = boardTagRepository.findAll()
        assertThat(savedBoardTags.size).isEqualTo(size)
    }
}