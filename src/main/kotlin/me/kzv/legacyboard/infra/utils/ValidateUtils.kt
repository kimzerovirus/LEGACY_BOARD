package me.kzv.legacyboard.infra.utils

fun validateWriter(writerId: Long, authenticatedId: Long) {
    require(writerId == authenticatedId) { "유효하지 않은 접근입니다." }
}