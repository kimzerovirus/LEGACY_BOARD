package me.kzv.legacyboard.board

enum class SearchType {
    NONE, ALL, TITLE, CONTENT, NICKNAME, TAG;

    companion object {
        fun of(param: String?): SearchType {
            return when (param) {
                null  -> NONE
                "title" -> TITLE
                "content" -> CONTENT
                "nickname" -> NICKNAME
                "tag" -> TAG
                else -> ALL
            }
        }
    }
}
