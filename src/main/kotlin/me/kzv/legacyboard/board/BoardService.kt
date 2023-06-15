package me.kzv.legacyboard.board

import me.kzv.legacyboard.infra.exception.TisException
import me.kzv.legacyboard.tag.BoardTag
import me.kzv.legacyboard.tag.BoardTagRepository
import me.kzv.legacyboard.tag.Tag
import me.kzv.legacyboard.tag.TagService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val tagService: TagService
) {

    @Transactional
    fun write(board: Board, tags: List<Tag>): Long {
        val savedBoard = boardRepository.save(board)
        tagService.searchAndCreate(savedBoard, tags)
        return savedBoard.id!!
    }

    @Transactional
    fun getBoardOneWithReplyList(boardId: Long): Board {
        val board = boardRepository.getBoardOneWithReplyList(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        board.updateCount()
        return board
    }

    @Transactional(readOnly = true)
    fun getBoardOne(boardId: Long): Board {
        return boardRepository.getBoardOne(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
    }

    @Transactional(readOnly = true)
    fun getBoardList(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board> {
        return boardRepository.search(searchType, keyword, pageable)
    }

    @Transactional
    fun edit(boardId: Long, title: String, content: String, tags: List<Tag>) {
        val board = boardRepository.getBoardOne(boardId) ?: throw TisException("게시글이 존재하지 않습니다.")
        tagService.updateBoardTag(board, tags)
        board.update(title = title, content = content)
    }

    @Transactional
    fun delete(boardId: Long) {
        boardRepository.deleteById(boardId)
        tagService.deleteBoardTag(boardId)
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