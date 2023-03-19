package me.kzv.legacyboard.entity

import jakarta.persistence.*

@Entity
class Board(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,
) : BaseEntity() {
    var count: Int = 0
}