package me.kzv.legacyboard.entity

import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.member.Member
import me.kzv.legacyboard.reply.Reply

class ReplyTest {

}

fun createReply(
    member: Member,
    board: Board,
    content: String = "reply"
): Reply {
    return Reply(member, board, content)
}