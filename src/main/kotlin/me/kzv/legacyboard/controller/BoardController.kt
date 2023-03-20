package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.CreateBoardRequest
import me.kzv.legacyboard.controller.dtos.EditBoardRequest
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.service.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/")
    fun home(
        model: Model,
        @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable
    ): String {
        model.addAttribute("boardList", boardService.getList(pageable))
        return "index"
    }

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write"
    }

    @ResponseBody
    @PostMapping("api/v1/board/write")
    fun createBoard(@RequestBody dto: CreateBoardRequest, authentication: Authentication): ResponseEntity<Any> {
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
    fun editBoard(@RequestBody dto: EditBoardRequest, authentication: Authentication): ResponseEntity<Any> {
        val member = authentication.principal as Member
        dto.validateWriter(member)
        boardService.write(dto.toEntity(member))
        return ResponseEntity.ok().build()
    }

}