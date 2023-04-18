package me.kzv.legacyboard.reply

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import me.kzv.legacyboard.infra.utils.validateWriter
import me.kzv.legacyboard.member.CurrentMember
import me.kzv.legacyboard.member.Member
import me.kzv.legacyboard.reply.dto.*
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
        @CurrentMember member: Member
    ): ResponseDto<List<ReplyResponseDto>> {
        val replyList = replyService.create(dto.content, dto.boardId, member).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }

    @ResponseBody
    @PostMapping("/api/v1/reply/edit")
    fun editReply(
        @RequestBody dto: EditReplyRequestDto,
        @CurrentMember member: Member
    ): ResponseDto<List<ReplyResponseDto>> {
        validateWriter(writerId = dto.replyId, authenticatedId = member.id!!)
        val replyList = replyService.edit(dto.content, replyId = dto.replyId, boardId = dto.boardId, ).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }

    @ResponseBody
    @PostMapping("/api/v1/reply/delete")
    fun deleteReply(
        @RequestBody dto: DeleteReplyRequestDto,
        @CurrentMember member: Member
    ): ResponseDto<List<ReplyResponseDto>> {
        validateWriter(writerId = dto.replyId, authenticatedId = member.id!!)
        val replyList = replyService.delete(dto.replyId, dto.boardId).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }
}