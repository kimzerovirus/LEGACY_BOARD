package me.kzv.legacyboard.test

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/test")
    fun test(): ResponseDto<String> {
        return ResponseDto(data = "test")
    }
}