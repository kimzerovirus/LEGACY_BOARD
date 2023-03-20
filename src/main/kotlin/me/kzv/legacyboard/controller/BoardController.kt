package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.CreateBoardRequestDto
import me.kzv.legacyboard.controller.dtos.EditBoardRequestDto
import me.kzv.legacyboard.controller.dtos.PageRequestDto
import me.kzv.legacyboard.controller.dtos.PageResponseDto
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/", "")
    fun home( model: Model, @RequestParam page: Int?): String {
        val boardList = boardService.getList(PageRequestDto(page))
        model.addAttribute("boardList", PageResponseDto(boardList))
        return "index"
    }

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write"
    }

    @ResponseBody
    @PostMapping("api/v1/board/write")
    fun createBoard(@RequestBody dto: CreateBoardRequestDto, authentication: Authentication): ResponseEntity<Any> {
        val member = authentication.principal as Member
        boardService.write(dto.toEntity(member))
        return ResponseEntity.ok().build()
    }

    @GetMapping("/board/edit")
    fun edit(): String {
        return "board/edit"
    }

    @ResponseBody
    @PostMapping("api/v1/board/edit")
    fun editBoard(@RequestBody dto: EditBoardRequestDto, authentication: Authentication): ResponseEntity<Any> {
        val member = authentication.principal as Member
        dto.validateWriter(member)
        boardService.write(dto.toEntity(member))
        return ResponseEntity.ok().build()
    }

}