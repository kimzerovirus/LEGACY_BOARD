package me.kzv.legacyboard.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.NoHandlerFoundException


@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle404(e: NoHandlerFoundException): String {
        println("404")
        return "error/notfound"
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(e: Exception): String {
        return "error/error"
    }
}