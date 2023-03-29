package me.kzv.legacyboard.board

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import me.kzv.legacyboard.infra.common.dto.PageRequestDto
import me.kzv.legacyboard.infra.common.dto.PageResponseDto
import me.kzv.legacyboard.infra.exception.TisException
import me.kzv.legacyboard.board.dto.CreateBoardRequestDto
import me.kzv.legacyboard.board.dto.CreateBoardResponseDto
import me.kzv.legacyboard.board.dto.DeleteBoardRequestDto
import me.kzv.legacyboard.board.dto.EditBoardRequestDto
import me.kzv.legacyboard.member.Member
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping("/", "")
    fun home(model: Model, @RequestParam type: String?,
             @RequestParam keyword: String?, @RequestParam page: Int?
    ): String {
        val boardList = boardService.getList(SearchType.of(type), keyword ?: "", PageRequestDto(page))
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
    fun createBoard(@RequestBody dto: CreateBoardRequestDto, authentication: Authentication): ResponseDto<CreateBoardResponseDto> {
        val member = authentication.principal as Member
        val id = boardService.write(dto.toEntity(member))
        return ResponseDto(data = CreateBoardResponseDto(id))
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
    @PostMapping("api/v1/board/edit/{boardId}")
    fun editBoard(@PathVariable boardId: Long, @RequestBody dto: EditBoardRequestDto, authentication: Authentication): ResponseDto<Any> {
        val member = authentication.principal as Member
        validateWriter(writerId = dto.memberId, authenticatedId = member.id!!)
        boardService.edit(boardId, title = dto.title, content = dto.content)
        return ResponseDto()
    }


    @ResponseBody
    @PostMapping("api/v1/board/delete")
    fun deleteBoard(@RequestBody dto: DeleteBoardRequestDto, authentication: Authentication): ResponseDto<Any> {
        val member = authentication.principal as Member
        validateWriter(writerId = dto.memberId, authenticatedId = member.id!!)
        boardService.delete(dto.boardId)
        return ResponseDto()
    }

    private fun validateWriter(writerId: Long, authenticatedId: Long) {
        if (writerId != authenticatedId) throw TisException("로그인한 회원과 글 작성자가 다름!!!")
    }
}