package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.*
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.exception.TisException
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
    fun home(model: Model, @RequestParam page: Int?): String {
        val boardList = boardService.getList(PageRequestDto(page))
        model.addAttribute("boardList", PageResponseDto(boardList))
        return "index"
    }

    @GetMapping("/board/view/{id}")
    fun getBoard(@PathVariable id: Long, model: Model): String {
        model.addAttribute("board", boardService.read(id))
        return "board/boardview"
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

    @GetMapping("/board/edit/{id}")
    fun edit(@PathVariable id: Long, authentication: Authentication, model: Model): String {
        val member = authentication.principal as Member
        val board = boardService.getOne(id)
        try {
            validateWriter(writerId = board.member.id!!, authenticatedId = member.id!!)
        } catch (e: TisException) {
            return "redirect:/"
        }
        model.addAttribute("board", board)
        return "board/edit"
    }

    @ResponseBody
    @PostMapping("api/v1/board/edit")
    fun editBoard(@RequestBody dto: EditBoardRequestDto, authentication: Authentication): ResponseEntity<Any> {
        val member = authentication.principal as Member
        validateWriter(writerId = dto.memberId, authenticatedId = member.id!!)
        boardService.edit(dto.boardId, title = dto.title, content = dto.content)
        return ResponseEntity.ok().build()
    }


    @ResponseBody
    @PostMapping("api/v1/board/delete")
    fun deleteBoard(@RequestBody dto: DeleteBoardRequestDto, authentication: Authentication): ResponseEntity<Any> {
        val member = authentication.principal as Member
        validateWriter(writerId = dto.memberId, authenticatedId = member.id!!)
        boardService.delete(dto.boardId)
        return ResponseEntity.ok().build()
    }

    private fun validateWriter(writerId: Long, authenticatedId: Long) {
        if (writerId != authenticatedId) throw TisException("로그인한 회원과 글 작성자가 다름!!!")
    }
}