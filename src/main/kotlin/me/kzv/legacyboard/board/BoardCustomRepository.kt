package me.kzv.legacyboard.board

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardCustomRepository {
    fun search(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board>
}