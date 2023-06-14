package me.kzv.legacyboard.board

import jakarta.persistence.EntityNotFoundException
import me.kzv.legacyboard.member.MemberRepository
import me.kzv.legacyboard.member.createMember
import me.kzv.legacyboard.tag.BoardTagRepository
import me.kzv.legacyboard.tag.Tag
import me.kzv.legacyboard.tag.TagRepository
import me.kzv.legacyboard.tag.createTag
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired lateinit var boardService: BoardService
    @Autowired lateinit var boardRepository: BoardRepository
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var boardTagRepository: BoardTagRepository
    @Autowired lateinit var tagRepository: TagRepository

    @Test
    fun `글쓰기 테스트`() {
        val member = memberRepository.save(createMember())
        val board = createBoard(member = member)
        val tags = mutableListOf<Tag>()
        (1..5).forEach { tags.add(createTag("태그$it")) }
        for (tag in tags) {
            println(tag.name)
        }

        val id = boardService.write(board, tags)
        val savedBoard = boardRepository.findAll().first()
        val findbyboardId = boardTagRepository.findAll()
        val savedTags = tagRepository.findAll()

        println(savedTags.size)

        println(findbyboardId.size)
        println(savedBoard.tags.size)
        for (tag in savedBoard.tags) {
            println(tag.tag.name)
        }
        println(id)
    }
}