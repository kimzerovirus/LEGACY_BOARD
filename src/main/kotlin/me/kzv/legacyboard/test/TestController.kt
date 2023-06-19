package me.kzv.legacyboard.test

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController {
    @GetMapping("/test")
    fun index() = "test"

    @ResponseBody
    @GetMapping("/test/api")
    fun test() = ResponseDto(data = "test")
}