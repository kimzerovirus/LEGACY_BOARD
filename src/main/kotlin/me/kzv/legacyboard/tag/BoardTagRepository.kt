package me.kzv.legacyboard.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BoardTagRepository : JpaRepository<BoardTag, Long> {
    fun deleteByBoard_id(boardId: Long)

    @Modifying
    @Query("delete from BoardTag bt where bt.board.id = :boardId")
    fun deleteQueryByBoardId(@Param("boardId") boardId: Long)
}