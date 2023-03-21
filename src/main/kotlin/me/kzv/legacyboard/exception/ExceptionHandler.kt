package me.kzv.legacyboard.exception

import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.NoHandlerFoundException
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle404(e: NoHandlerFoundException): String {
        return "error/notfound"
    }

    @ExceptionHandler(TisException::class)
    fun tisExceptionHandler(e: Exception, model: Model): String {
        model.addAttribute("error", e.message)
        return "error/error"
    }

    @ExceptionHandler(RuntimeException::class)
    fun globalExceptionHandler(e: Exception, model: Model): String {
        model.addAttribute("error", "서버에러 발생 - 관리자에게 문의해주세요")
        return "error/error"
    }
}