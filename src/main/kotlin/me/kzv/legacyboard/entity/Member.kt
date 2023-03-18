package me.kzv.legacyboard.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import me.kzv.legacyboard.entity.enums.RoleType

@Entity
class Member(
    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: RoleType = RoleType.USER
) : BaseEntity() {
}