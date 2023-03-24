package me.kzv.legacyboard.repository

import jakarta.persistence.EntityNotFoundException
import me.kzv.legacyboard.entity.createBoard
import me.kzv.legacyboard.entity.createMember
import me.kzv.legacyboard.entity.createReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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

}