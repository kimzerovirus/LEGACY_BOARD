package me.kzv.legacyboard.board

import me.kzv.legacyboard.board.Board
import me.kzv.legacyboard.member.Member

class BoardTest {

}

fun createBoard(
    title: String = "test",
    content: String = "test",
    member: Member
): Board {
    return Board(member, title = title, content = content)
}