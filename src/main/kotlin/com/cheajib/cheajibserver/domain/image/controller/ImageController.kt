package com.cheajib.cheajibserver.domain.image.controller

import com.cheajib.cheajibserver.domain.image.controller.dto.response.ImageListResponse
import com.cheajib.cheajibserver.domain.image.service.ImageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
    private val imageService: ImageService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("images")
    fun imageUpload(@RequestParam image: List<MultipartFile>): ImageListResponse {
        return imageService.imageUpload(image)
    }
}
