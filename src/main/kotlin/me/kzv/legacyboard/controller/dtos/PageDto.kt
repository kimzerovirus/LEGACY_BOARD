package me.kzv.legacyboard.controller.dtos

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import kotlin.math.ceil

const val pageSize = 8

class PageRequestDto(
    page: Int?,
) : PageRequest(
    (page ?: 1).minus(1), // pageable 0부터 시작
    pageSize, Sort.by("id").descending())

class PageResponseDto(page: Page<*>) {
    val content: List<*> = page.content
    val totalPage = page.totalPages

    var pageList = mutableListOf<Int>()
    var cpage: Int = 0 // 현재 페이지
    var checkPrev: Boolean = false // 이전, 다음 페이지 유무
    var checkNext: Boolean = false

    init {
        cpage = page.pageable.pageNumber + 1 // pageable 0부터 시작
        val tempEnd = ceil(cpage / pageSize.toDouble()).toInt() * pageSize
        val startPage = tempEnd - (pageSize - 1)
        val endPage = if (totalPage > tempEnd) tempEnd else totalPage
        checkPrev = startPage > 1
        checkNext = totalPage > tempEnd
        pageList = (startPage..endPage).map { it }.toMutableList()
    }
}