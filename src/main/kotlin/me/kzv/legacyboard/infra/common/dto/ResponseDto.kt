package me.kzv.legacyboard.infra.common.dto

import org.springframework.http.HttpStatus

class ResponseDto<T>(
    val status: Int = HttpStatus.OK.value(),
    val data: T? = null
)
