package me.kzv.legacyboard.repository

import me.kzv.legacyboard.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}