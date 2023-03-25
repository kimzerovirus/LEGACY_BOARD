package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.CreateReplyRequestDto
import me.kzv.legacyboard.controller.dtos.ReplyResponseDto
import me.kzv.legacyboard.controller.dtos.ResponseDto
import me.kzv.legacyboard.controller.dtos.toResponseDto
import me.kzv.legacyboard.entity.Member
import me.kzv.legacyboard.entity.Reply
import me.kzv.legacyboard.service.ReplyService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ReplyController(
    private val replyService: ReplyService,
) {
    @ResponseBody
    @PostMapping("/api/v1/reply/create/{boardId}")
    fun createReply(
        @PathVariable boardId: Long,
        @RequestBody dto: CreateReplyRequestDto,
        authentication: Authentication
    ): ResponseDto<List<ReplyResponseDto>> {
        val member = authentication.principal as Member
        val replyList = replyService.create(dto.content, boardId, member).map { it.toResponseDto(member.id!!) }
        return ResponseDto(data = replyList)
    }
}