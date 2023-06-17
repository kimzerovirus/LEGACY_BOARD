package me.kzv.legacyboard.member.dto

import java.sql.Timestamp
import java.time.LocalDateTime

data class MemberActivityResponseDto(
    val id: Long,
    val replyId: Long?,
    val memberId: Long,
    val content: String,
    val tableName: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun of(map: Map<String, Any>): MemberActivityResponseDto {
            return MemberActivityResponseDto(
                map["ID"] as Long,
                map["REPLY_ID"] as Long?,
                map["MEMBER_ID"] as Long,
                map["CONTENT"] as String,
                map["TABLE_NAME"] as String,
                (map["CREATED_AT"] as Timestamp).toLocalDateTime()
            )
        }
    }
}