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
    fun write(board: Board) {
        boardRepository.save(board)
    }

    @Transactional(readOnly = true)
    fun getOne(boardId: Long): Board {
        return boardRepository.findByIdOrNull(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
    }

    @Transactional
    fun read(boardId: Long): Board {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        board.update()
        return board
    }

    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): Page<Board> {
        return boardRepository.findAll(pageable)
    }

    @Transactional
    fun update(board: Board) {
        boardRepository.save(board)
    }

    @Transactional
    fun delete(board: Board) {
        boardRepository.delete(board)
    }
}