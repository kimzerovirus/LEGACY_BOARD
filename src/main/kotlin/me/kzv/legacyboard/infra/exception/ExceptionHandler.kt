package me.kzv.legacyboard.infra.exception

import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.NoHandlerFoundException
import java.lang.IllegalStateException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundHandler(e: NoHandlerFoundException): String {
        return "error/notfound"
    }

    @ExceptionHandler(TisException::class, IllegalStateException::class)
    fun tisExceptionHandler(e: Exception, model: Model): String {
        model.addAttribute("error", e.message)
        return "error/error"
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(e: Exception, model: Model): String {
        model.addAttribute("error", "서버에러 발생 - 관리자에게 문의해주세요")
        return "error/error"
    }
}