package me.kzv.legacyboard.file

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Profile("prod")
@Service
class S3FileService : FileService {
    override fun uploadFile(file: MultipartFile): String {
        TODO("Not yet implemented")
    }
}