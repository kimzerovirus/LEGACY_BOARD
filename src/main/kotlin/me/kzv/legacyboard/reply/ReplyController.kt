package me.kzv.legacyboard.reply

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import me.kzv.legacyboard.member.Member
import me.kzv.legacyboard.reply.dto.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ReplyController(
    private val replyService: ReplyService,
) {
    @ResponseBody
    @PostMapping("/api/v1/reply/create")
    fun createReply(
        @RequestBody dto: CreateReplyRequestDto,
        authentication: Authentication
    ): ResponseDto<List<ReplyResponseDto>> {
        val member = authentication.principal as Member
        val replyList = replyService.create(dto.content, dto.boardId, member).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }

    @ResponseBody
    @PostMapping("/api/v1/reply/edit")
    fun editReply(
        @RequestBody dto: EditReplyRequestDto,
        authentication: Authentication
    ): ResponseDto<List<ReplyResponseDto>> {
        val member = authentication.principal as Member
        val replyList = replyService.edit(dto.content, replyId = dto.replyId, boardId = dto.boardId, ).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }

    @ResponseBody
    @PostMapping("/api/v1/reply/delete")
    fun deleteReply(
        @RequestBody dto: DeleteReplyRequestDto,
        authentication: Authentication
    ): ResponseDto<List<ReplyResponseDto>> {
        val member = authentication.principal as Member
        val replyList = replyService.delete(dto.replyId, dto.boardId).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }
}