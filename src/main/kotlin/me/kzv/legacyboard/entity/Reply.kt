package me.kzv.legacyboard.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class Reply(
    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    val board: Board,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,
) : BaseEntity() {
    fun update(content: String) {
        this.content = content
    }
}