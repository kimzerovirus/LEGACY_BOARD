package me.kzv.legacyboard.board

import me.kzv.legacyboard.infra.exception.TisException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.regex.Pattern

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
    fun getList(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board> {
        return boardRepository.search(searchType, keyword, pageable)
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

//    private fun extractImgAndCreateImgList(content: String): MutableList<String> {
//        val imgList = mutableListOf<String>()
//        val pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>")
//        val matcher = pattern.matcher(content)
//        while (matcher.find()){
//            imgList.add(matcher.group(1))
//        }
//        return imgList
//    }
}