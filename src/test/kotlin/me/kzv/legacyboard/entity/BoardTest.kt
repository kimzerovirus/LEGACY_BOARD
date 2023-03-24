package me.kzv.legacyboard.entity

import org.junit.jupiter.api.Assertions.*

class BoardTest {

}

fun createBoard(
    title: String = "test",
    content: String = "test",
    member: Member
): Board {
    return Board(member, title = title, content = content)
}