package me.kzv.legacyboard.member

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun findByEmail(email: String): Member?
    fun findByNickname(nickname: String): Member?

    @Query(
        value = "select *\n" +
                "from (\n" +
                "         SELECT id,\n" +
                "                null      AS reply_id,\n" +
                "                title AS content,\n" +
                "                created_at,\n" +
                "                'BOARD' AS table_name,\n" +
                "                member_id\n" +
                "         FROM board\n" +
                "         UNION\n" +
                "         SELECT board_id,\n " +
                "                id,\n" +
                "                content,\n" +
                "                created_at,\n" +
                "                'REPLY' AS table_name,\n" +
                "                member_id\n" +
                "         FROM reply\n" +
                "     ) AS u\n" +
                "where u.member_id = :memberId\n" +
                "order by created_at desc",
        countQuery = "select count(*)\n" +
                "from (\n" +
                "         select member_id, id, null\n" +
                "         from board\n" +
                "         union\n" +
                "         select member_id, board_id, id\n" +
                "         from reply\n" +
                "     ) AS u\n" +
                "where u.member_id = :memberId",
        nativeQuery = true
    )
    fun getMemberActivity(@Param("memberId") memberId: Long, pageable: Pageable): Page<Map<String, Any>>
}