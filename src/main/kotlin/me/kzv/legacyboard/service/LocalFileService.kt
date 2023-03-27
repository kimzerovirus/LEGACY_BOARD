package me.kzv.legacyboard.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.util.*

@Profile("local")
@Service
class LocalFileService : FileService {

    @Value("\${uploadPath}")
    lateinit var uploadPath: String

    override fun uploadFile(file: MultipartFile): String {
        if (file.isEmpty) throw FileNotFoundException()
        val saveImgName: String = UUID.randomUUID().toString() + extractExt(file.originalFilename!!)
        val fileUploadFullUrl = uploadPath + saveImgName
        file.transferTo(File(fileUploadFullUrl))
        val imgUrl = "http://localhost:8080/images/$saveImgName"
        return imgUrl
    }
}