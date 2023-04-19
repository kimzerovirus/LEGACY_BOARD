package me.kzv.legacyboard.board

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import me.kzv.legacyboard.infra.common.dto.PageRequestDto
import me.kzv.legacyboard.infra.common.dto.PageResponseDto
import me.kzv.legacyboard.infra.exception.TisException
import me.kzv.legacyboard.board.dto.CreateBoardRequestDto
import me.kzv.legacyboard.board.dto.CreateBoardResponseDto
import me.kzv.legacyboard.board.dto.DeleteBoardRequestDto
import me.kzv.legacyboard.board.dto.EditBoardRequestDto
import me.kzv.legacyboard.member.CurrentMember
import me.kzv.legacyboard.member.Member
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping("/", "")
    fun home(
        model: Model, @RequestParam type: String?,
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
    fun createBoard(
        @RequestBody dto: CreateBoardRequestDto,
        @CurrentMember member: Member
    ): ResponseDto<CreateBoardResponseDto> {
        val id = boardService.write(dto.toEntity(member))
        return ResponseDto(data = CreateBoardResponseDto(id))
    }

    @GetMapping("/board/edit/{id}")
    fun edit(
        @PathVariable id: Long,
        @CurrentMember member: Member,
        model: Model
    ): String {
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
    fun editBoard(
        @PathVariable boardId: Long,
        @RequestBody dto: EditBoardRequestDto,
        @CurrentMember member: Member
    ): ResponseDto<Any> {
        with(dto) {
            validateWriter(writerId = memberId, authenticatedId = member.id!!)
            boardService.edit(boardId, title = title, content = content)
        }
        return ResponseDto()
    }


    @ResponseBody
    @PostMapping("api/v1/board/delete")
    fun deleteBoard(
        @RequestBody dto: DeleteBoardRequestDto,
        @CurrentMember member: Member
    ): ResponseDto<Any> {
        validateWriter(writerId = dto.memberId, authenticatedId = member.id!!)
        boardService.delete(dto.boardId)
        return ResponseDto()
    }

    private fun validateWriter(writerId: Long, authenticatedId: Long) {
        require(writerId == authenticatedId) { "유효하지 않은 접근입니다." }
    }

}