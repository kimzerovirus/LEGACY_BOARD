package me.kzv.legacyboard.controller

import me.kzv.legacyboard.controller.dtos.FileResponseDto
import me.kzv.legacyboard.controller.dtos.ResponseDto
import me.kzv.legacyboard.service.FileService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(
    private val fileService: FileService
) {
    @PostMapping("/api/v1/file/upload")
    fun upload(file: MultipartFile): ResponseDto<FileResponseDto> {
        println(file)
        val url = fileService.uploadFile(file)
        return ResponseDto(data = FileResponseDto(url))
    }
}