package com.cheajib.cheajibserver.domain.image.service

import com.cheajib.cheajibserver.domain.image.controller.dto.response.ImageListResponse
import com.cheajib.cheajibserver.infrastructure.aws.facade.ImageFacade
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(
    private val imageFacade: ImageFacade
) {
    fun imageUpload(images: List<MultipartFile>): ImageListResponse {
        val imageList: MutableList<String> = mutableListOf()

        for (image: MultipartFile in images) {
            imageList.add(imageFacade.upload(image))
        }

        return ImageListResponse(imageList)
    }
}
