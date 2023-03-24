package me.kzv.legacyboard.service

import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.entity.Reply
import me.kzv.legacyboard.exception.TisException
import me.kzv.legacyboard.repository.BoardRepository
import me.kzv.legacyboard.repository.ReplyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReplyService(
    private val replyRepository: ReplyRepository,
    private val boardRepository: BoardRepository
) {
    @Transactional
    fun create(content: String, boardId: Long, member: Member): List<Reply>  {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        val reply = Reply(member, board, content)
        replyRepository.save(reply)
        return getList(boardId)
    }

    @Transactional
    fun edit(content: String, replyId: Long, boardId: Long): List<Reply>  {
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw TisException("댓글이 존재하지 않습니다.")
        reply.update(content)
        return getList(boardId)
    }

    @Transactional
    fun delete(replyId: Long, boardId: Long): List<Reply>  {
        replyRepository.deleteById(replyId)
        return getList(boardId)
    }

    private fun getList(boardId: Long): List<Reply> {
        return replyRepository.findByBoard_id(boardId)
    }
}