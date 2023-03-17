package me.kzv.legacyboard.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/hello")
    fun jsp(model: Model): String{
        model.addAttribute("username", "KZV")
        return "hello"
    }
}