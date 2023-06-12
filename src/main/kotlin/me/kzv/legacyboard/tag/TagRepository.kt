package me.kzv.legacyboard.tag

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByNameLike(name: String): List<Tag>
}