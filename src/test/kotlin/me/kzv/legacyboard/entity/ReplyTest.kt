package me.kzv.legacyboard.entity

import org.junit.jupiter.api.Assertions.*

class ReplyTest {

}

fun createReply(
    member: Member,
    board: Board,
    content: String = "reply"
): Reply{
    return Reply(member, board, content)
}