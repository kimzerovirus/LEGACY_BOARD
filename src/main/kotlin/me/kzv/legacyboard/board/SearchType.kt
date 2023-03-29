package me.kzv.legacyboard.board

enum class SearchType {
    ALL, TITLE, CONTENT, NICKNAME;

    companion object {
        fun of(param: String?): SearchType {
            return when (param) {
                "title" -> TITLE
                "content" -> CONTENT
                "nickname" -> NICKNAME
                else -> ALL
            }
        }
    }
}
