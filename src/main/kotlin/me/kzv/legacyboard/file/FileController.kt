package me.kzv.legacyboard.file

import me.kzv.legacyboard.file.dto.FileResponseDto
import me.kzv.legacyboard.infra.common.dto.ResponseDto
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