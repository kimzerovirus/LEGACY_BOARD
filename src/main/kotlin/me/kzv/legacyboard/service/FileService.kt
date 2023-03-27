package me.kzv.legacyboard.service

import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun uploadFile(file: MultipartFile): String

//    fun removeFile()

    fun extractExt(originalFilename: String): String {
        val pos = originalFilename.lastIndexOf(".")
        return "." + originalFilename.substring(pos + 1)
    }
}