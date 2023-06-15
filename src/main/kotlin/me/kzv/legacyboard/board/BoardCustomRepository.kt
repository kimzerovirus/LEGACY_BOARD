package me.kzv.legacyboard.board

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardCustomRepository {
    fun getBoardOne(id: Long): Board?

    fun getBoardOneWithReplyList(id: Long): Board?

    fun search(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board>
}