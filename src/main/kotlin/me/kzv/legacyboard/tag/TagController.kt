package me.kzv.legacyboard.tag

import me.kzv.legacyboard.infra.common.dto.ResponseDto
import me.kzv.legacyboard.tag.dto.SearchTagRequestDto
import me.kzv.legacyboard.tag.dto.SearchTagResponseDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TagController(
    private val tagService: TagService
) {
    @ResponseBody
    @PostMapping("/api/tag/search")
    fun search(@RequestBody dto: SearchTagRequestDto): ResponseDto<SearchTagResponseDto> {
        return ResponseDto(data = SearchTagResponseDto(tagService.search(dto.name)))
    }
}