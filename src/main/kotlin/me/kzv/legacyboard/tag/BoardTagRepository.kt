package me.kzv.legacyboard.tag

import org.springframework.data.jpa.repository.JpaRepository

interface BoardTagRepository : JpaRepository<BoardTag, Long> {
}