package me.kzv.legacyboard.repository

import jakarta.persistence.EntityNotFoundException
import me.kzv.legacyboard.board.SearchType
import me.kzv.legacyboard.entity.createBoard
import me.kzv.legacyboard.entity.createMember
import me.kzv.legacyboard.entity.createReply
import me.kzv.legacyboard.board.BoardRepository
import me.kzv.legacyboard.member.MemberRepository
import me.kzv.legacyboard.reply.ReplyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class BoardRepositoryTest {
    @Autowired lateinit var boardRepository: BoardRepository
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var replyRepository: ReplyRepository

    @Test
    fun `findWithReplyListById - 엔티티그래프 테스트`() {
        val title = "test title"
        val content = "test content"
        val member = memberRepository.save(createMember())
        val board = boardRepository.save(createBoard(title = title, content = content, member))
        replyRepository.save(createReply(member, board))

        val boardWithMemberAndReply = boardRepository.findWithMemberAndReplyById(board.id!!) ?: throw EntityNotFoundException()

        assertThat(boardWithMemberAndReply.title).isEqualTo(title)
        assertThat(boardWithMemberAndReply.content).isEqualTo(content)
        assertThat(boardWithMemberAndReply.replyList.size).isEqualTo(1)
    }

    @EnumSource(value = SearchType::class, names = ["ALL"], mode = Mode.EXCLUDE)
    @ParameterizedTest
    fun `keyword로 검색 - 글 제목, 글 내용, 닉네임`(searchType: SearchType) {
        val keyword = "test123@test.com"
        val member = memberRepository.save(createMember(nickname = keyword))
        val board = boardRepository.save(createBoard(title = keyword, content = keyword, member))
        replyRepository.save(createReply(member, board))
        val pageable = PageRequest.of(0,10)

        val boardList = boardRepository.search(searchType, keyword, pageable)

        assertThat(boardList.content.size).isEqualTo(1)
    }

    @Test
    fun `keyword와 search type이 없는 경우`() {
        val pageable = PageRequest.of(0,10)

        val boardList = boardRepository.search(pageable = pageable)

        assertThat(boardList.content.size).isEqualTo(10)
    }
}