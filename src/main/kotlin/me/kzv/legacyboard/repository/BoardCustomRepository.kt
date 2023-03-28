package me.kzv.legacyboard.repository

import me.kzv.legacyboard.entity.Board
import me.kzv.legacyboard.entity.SearchType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardCustomRepository {
    fun search(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board>
}