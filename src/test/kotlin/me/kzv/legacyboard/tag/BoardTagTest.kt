package me.kzv.legacyboard.tag

import me.kzv.legacyboard.board.BoardRepository
import me.kzv.legacyboard.board.BoardService
import me.kzv.legacyboard.board.createBoard
import me.kzv.legacyboard.member.MemberRepository
import me.kzv.legacyboard.member.createMember
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class BoardTagTest {
    @Autowired lateinit var boardService: BoardService
    @Autowired lateinit var boardRepository: BoardRepository
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var boardTagRepository: BoardTagRepository
    @Autowired lateinit var tagRepository: TagRepository

    @BeforeEach
    fun init(){
        val member = memberRepository.save(createMember())
        boardRepository.save(createBoard(member = member))
    }

    @Test
    fun `jpql delete by board_id(fk) query테스트`() {
        val tag = tagRepository.save(createTag())
        val board = boardRepository.findAll().first()
        boardTagRepository.save(BoardTag(board, tag))

        boardTagRepository.deleteQueryByBoardId(board.id!!)

        val list = boardTagRepository.findAll()
        assertThat(list).isEmpty()
    }
}