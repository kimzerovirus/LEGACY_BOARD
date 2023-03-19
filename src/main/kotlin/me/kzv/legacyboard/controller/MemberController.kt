package me.kzv.legacyboard.controller

import jakarta.validation.Valid
import me.kzv.legacyboard.controller.dtos.SignupRequest
import me.kzv.legacyboard.controller.validators.SignupRequestValidator
import me.kzv.legacyboard.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController (
    private val memberService: MemberService,
    private val signupRequestValidator: SignupRequestValidator
){
    @InitBinder("signupRequest")
    fun initBinder(data: WebDataBinder) {
        data.addValidators(signupRequestValidator)
    }

    @GetMapping("/signup")
    fun signupPage(): String {
        return "member/signup"
    }

    @PostMapping("/signup")
    fun signup(
        @ModelAttribute("signupRequest") @Valid dto: SignupRequest,
        bindingResult: BindingResult, model: Model): String {
        if(bindingResult.hasErrors()){
            model.addAttribute("email", dto.email)
            model.addAttribute("nickname", dto.nickname)
            return "member/signup"
        }
        memberService.createMember(dto)
        return "redirect:/"
    }

    @GetMapping("/signin")
    fun signinPage(): String {
        return "member/signin"
    }

    @GetMapping("/mypage")
    fun mypage():String {
        return "member/mypage"
    }
}