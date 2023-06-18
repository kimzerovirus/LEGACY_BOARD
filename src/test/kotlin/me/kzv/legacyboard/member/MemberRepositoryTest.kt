package me.kzv.legacyboard.member

import me.kzv.legacyboard.board.BoardRepository
import me.kzv.legacyboard.board.createBoard
import me.kzv.legacyboard.reply.ReplyRepository
import me.kzv.legacyboard.reply.createReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class MemberRepositoryTest {
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var boardRepository: BoardRepository
    @Autowired lateinit var replyRepository: ReplyRepository

    @Test
    fun `회원활동 목록 조회`() {
        val page = 0
        val size = 36
        val totalPage = if(size % 10 > 0) size / 10 + 1 else size / 10
        val member = memberRepository.save(createMember())
        val board = boardRepository.save(createBoard(member = member))
        (1..size).forEach {
            replyRepository.save(createReply(board = board, member = member, content = "$it"))
        }

        val memberActivity = memberRepository.getMemberActivity(member.id!!, PageRequest.of(page, 10))
        for (map in memberActivity.content) {
            println(map.keys)
            for (item in map) print("$item / ")
            println()
        }

        assertThat(memberActivity.content.size).isEqualTo(10)
        assertThat(memberActivity.pageable.pageNumber).isEqualTo(page)
        assertThat(memberActivity.totalPages).isEqualTo(totalPage)
    }
}