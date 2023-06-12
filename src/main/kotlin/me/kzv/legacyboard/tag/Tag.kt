package me.kzv.legacyboard.tag

import jakarta.persistence.Column
import jakarta.persistence.Entity
import me.kzv.legacyboard.infra.common.jpa.BaseEntity

@Entity
class Tag(
    /** 태그 이름 */
    @Column(nullable = false, unique = true)
    val name: String,
) : BaseEntity()