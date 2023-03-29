package me.kzv.legacyboard.board

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kzv.legacyboard.board.QBoard.board
import me.kzv.legacyboard.board.SearchType.*
import me.kzv.legacyboard.member.QMember.member
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable


class BoardCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : BoardCustomRepository {
    override fun search(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board> {
        val condition = searchCondition(searchType, keyword)

        val content = queryFactory
            .selectFrom(board)
            .leftJoin(board.member, member)
            .where(condition)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(board.id.desc())
            .fetch()

        val countQuery = queryFactory
            .select(board.count())
            .from(board)
            .leftJoin(board.member, member)
            .where(condition)
            .fetchOne()!!

        return PageImpl(content, pageable, countQuery)
    }

    private fun searchCondition(searchType: SearchType, keyword: String): BooleanExpression? {
        return when(searchType){
            TITLE -> titleContains(keyword)
            CONTENT -> contentContains(keyword)
            NICKNAME -> nicknameContains(keyword)
            else -> if (keyword != "") {
                titleContains(keyword).or(contentContains(keyword).or(nicknameContains(keyword)))
            } else null
        }
    }

    private fun titleContains(keyword: String): BooleanExpression = board.title.contains(keyword)
    private fun contentContains(keyword: String): BooleanExpression = board.content.contains(keyword)
    private fun nicknameContains(keyword: String): BooleanExpression = member.nickname.contains(keyword)
}