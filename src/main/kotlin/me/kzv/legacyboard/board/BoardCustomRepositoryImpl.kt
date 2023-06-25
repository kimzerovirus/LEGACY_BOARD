package me.kzv.legacyboard.board

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kzv.legacyboard.board.QBoard.board
import me.kzv.legacyboard.board.SearchType.*
import me.kzv.legacyboard.member.QMember.member
import me.kzv.legacyboard.reply.QReply.reply
import me.kzv.legacyboard.tag.QBoardTag.boardTag
import me.kzv.legacyboard.tag.QTag.tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class BoardCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : BoardCustomRepository {
    override fun getBoardOne(id: Long): Board? {
        return queryFactory
            .selectFrom(board)
            .leftJoin(board.member, member).fetchJoin()
            .leftJoin(board.tags, boardTag).fetchJoin()
            .leftJoin(boardTag.tag, tag).fetchJoin()
            .where(board.id.eq(id))
            .fetchOne()
    }

    override fun getBoardOneWithReplyList(id: Long): Board? {
        return queryFactory
            .selectFrom(board)
            .leftJoin(board.member, member).fetchJoin()
            .leftJoin(board.tags, boardTag).fetchJoin()
            .leftJoin(boardTag.tag, tag).fetchJoin()
            .leftJoin(board.replyList, reply).fetchJoin()
            .where(board.id.eq(id))
            .fetchOne()
    }

    override fun search(searchType: SearchType, keyword: String, pageable: Pageable): Page<Board> {
        val condition = searchCondition(searchType, keyword)

        val content = queryFactory
            .selectFrom(board)
            .leftJoin(board.member, member)
            .leftJoin(board.tags, boardTag)
            .leftJoin(boardTag.tag, tag)
            .distinct()
            .where(condition)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(board.id.desc())
            .fetch()
        // fetch join 하지 않으면 lazy 하게 가져옴
        // fetch join + pagination offset limit 가 적용되지 않고 실제로는 메모리상에서 작업함
        // 항상 1:N 작업해버리면 문제가 생기는듯?

        val countQuery = queryFactory
            .select(board.count())
            .from(board)
            .leftJoin(board.member, member)
            .where(condition)
            .fetchOne()!!

        return PageImpl(content, pageable, countQuery)
    }

    private fun searchCondition(searchType: SearchType, keyword: String): BooleanExpression? {
        return when (searchType) {
            NONE -> null
            ALL -> allContains(keyword)
            TITLE -> titleContains(keyword)
            CONTENT -> contentContains(keyword)
            NICKNAME -> nicknameContains(keyword)
            TAG -> tagContains(keyword)
        }
    }

    private fun titleContains(keyword: String) = board.title.contains(keyword)
    private fun contentContains(keyword: String) = board.content.contains(keyword)
    private fun nicknameContains(keyword: String) = member.nickname.contains(keyword)
    private fun tagContains(keyword: String) = board.tags.any().tag.name.contains(keyword)
    private fun allContains(keyword: String) = titleContains(keyword).or(contentContains(keyword)
                                                .or(nicknameContains(keyword).or(tagContains(keyword))))
}