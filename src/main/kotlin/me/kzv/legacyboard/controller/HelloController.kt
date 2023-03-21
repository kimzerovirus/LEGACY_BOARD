package me.kzv.legacyboard.controller

import me.kzv.legacyboard.exception.TisException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.lang.RuntimeException

@Controller
class HelloController {

    @GetMapping("/hello")
    fun jsp(model: Model): String{
        model.addAttribute("username", "KZV")
        return "hello"
    }

    @GetMapping("/tis-exception")
    fun tisException(){
        throw TisException("의도된 에러")
    }

    @GetMapping("/global-exception")
    fun globalException(){
        throw RuntimeException()
    }
}