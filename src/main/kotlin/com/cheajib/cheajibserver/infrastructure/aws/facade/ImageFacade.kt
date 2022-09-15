package com.cheajib.cheajibserver.infrastructure.aws.facade

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.cheajib.cheajibserver.infrastructure.aws.exception.ImageNotFoundException
import com.cheajib.cheajibserver.infrastructure.aws.exception.InvalidImageExtensionFormatException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Component
class ImageFacade(
    private val amazonS3Client: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,

    @Value("\${cloud.aws.s3.url}")
    private val baseUrl: String,
) {
    fun upload(multipartFile: MultipartFile): String {
        val extension: String? = getExtension(multipartFile)
        val imageUrl = "cheajib/" + UUID.randomUUID() + extension

        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = multipartFile.size
        objectMetadata.contentType = multipartFile.contentType

        try {
            amazonS3Client.putObject(
                PutObjectRequest(bucket, imageUrl, multipartFile.inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw ImageNotFoundException.EXCEPTION
        }

        return baseUrl + imageUrl
    }

    private fun getExtension(multipartFile: MultipartFile): String? {
        if (multipartFile.isEmpty || multipartFile.originalFilename == null) {
            throw ImageNotFoundException.EXCEPTION
        }

        val originalName: String? = multipartFile.originalFilename
        val extension: String? = originalName?.let { originalName.substring(it.lastIndexOf(".")) }

        if (!(extension == ".jpg" || extension == ".jpeg" || extension == ".png" || extension == ".heic")) {
            throw InvalidImageExtensionFormatException.EXCEPTION
        }

        return extension
    }
}
