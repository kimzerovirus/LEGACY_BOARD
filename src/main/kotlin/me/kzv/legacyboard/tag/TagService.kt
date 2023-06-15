package me.kzv.legacyboard.tag

import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.infra.exception.TisException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val boardTagRepository: BoardTagRepository
) {
    @Transactional(readOnly = true)
    fun search(name: String): List<Tag> {
        return tagRepository.findByNameLike("%$name%")
    }

    @Transactional
    fun searchAndCreate(board: Board, tags: List<Tag>) {
        tags.map {
            if (it.id == null) BoardTag(board, tagRepository.save(it))
            else BoardTag(board, it)
        }.forEach {
            boardTagRepository.save(it)
        }
    }

    @Transactional
    fun updateBoardTag(board: Board, tags: List<Tag>) {
        deleteBoardTag(board.id!!)
        boardTagRepository.saveAll(tags.map { BoardTag(board, it) })
    }

    @Transactional
    fun deleteBoardTag(boardId: Long) {
//        boardTagRepository.deleteByBoard_id(boardId) // 실제로는 fk로 삭제하지 않고 boardTag pk 를 찾아서 해당 pk 하나하나로 삭제함 slow query
        boardTagRepository.deleteQueryByBoardId(boardId)
    }
}
