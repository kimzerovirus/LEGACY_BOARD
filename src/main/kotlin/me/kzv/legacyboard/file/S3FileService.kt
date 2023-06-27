package me.kzv.legacyboard.file

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*


@Profile("prod")
@Service
class S3FileService (
    private val amazonS3Client: AmazonS3Client
) : FileService {

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    override fun uploadFile(file: MultipartFile): String {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = file.contentType
        objectMetadata.contentLength = file.size

        val fileName: String = "image/" + UUID.randomUUID() + "." + file.originalFilename

        try {
            file.inputStream.use { inputStream ->
                amazonS3Client.putObject(
                    PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                )
            }
        } catch (e: IOException) {
            throw IllegalStateException("S3 파일 업로드에 실패했습니다.")
        }

        return amazonS3Client.getUrl(bucket, fileName).toString()
    }
}