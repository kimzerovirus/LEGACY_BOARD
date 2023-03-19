package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.CreateBoardRequest
import me.kzv.legacyboard.controller.dtos.EditBoardRequest
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.service.BoardService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/")
    fun home(): String {
        return "index"
    }

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write"
    }

    @ResponseBody
    @PostMapping("api/v1/board/write")
    fun createBoard(@RequestBody dto: CreateBoardRequest, authentication: Authentication): String {
        val member = authentication.principal as Member // SecurityContextHolder
        boardService.write(dto.toEntity(member))
        return "board/write"
    }

    @GetMapping("/board/edit")
    fun edit(): String {
        return "board/edit"
    }

    @ResponseBody
    @PostMapping("api/v1/board/edit")
    fun editBoard(@RequestBody dto: EditBoardRequest, authentication: Authentication): String {
        val member = authentication.principal as Member
        dto.validateWriter(member)
        boardService.write(dto.toEntity(member))

        return "board/write"
    }

}