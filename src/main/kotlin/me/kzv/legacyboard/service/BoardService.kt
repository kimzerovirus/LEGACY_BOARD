package me.kzv.legacyboard.service

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.exception.TisException
import me.kzv.legacyboard.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    @Transactional
    fun write(board: Board): Long {
       return boardRepository.save(board).id!!
    }

    @Transactional(readOnly = true)
    fun getOne(boardId: Long): Board {
        return boardRepository.findWithMemberAndReplyById(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
    }

    @Transactional
    fun read(boardId: Long): Board {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        board.updateCount()
        return board
    }

    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): Page<Board> {
        return boardRepository.findAll(pageable)
    }

    @Transactional
    fun edit(boardId: Long, title: String, content: String) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        board.update(title = title, content = content)
    }

    @Transactional
    fun delete(boardId: Long) {
        boardRepository.deleteById(boardId)
    }
}