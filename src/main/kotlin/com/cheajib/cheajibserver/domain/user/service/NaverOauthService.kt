package com.cheajib.cheajibserver.domain.user.service

import com.cheajib.cheajibserver.domain.user.domain.User
import com.cheajib.cheajibserver.domain.user.domain.repository.UserRepository
import com.cheajib.cheajibserver.domain.user.domain.type.Level
import com.cheajib.cheajibserver.domain.user.domain.type.Sex
import com.cheajib.cheajibserver.infrastructure.feign.client.NaverTokenClient
import com.cheajib.cheajibserver.infrastructure.feign.client.NaverUserInfoClient
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.NaverUserInfoElement
import com.cheajib.cheajibserver.infrastructure.feign.dto.response.TokenResponse
import com.cheajib.cheajibserver.infrastructure.feign.properties.NaverFeignProperties
import org.springframework.stereotype.Service
import java.util.*

@Service
class NaverOauthService(
    private val naverTokenClient: NaverTokenClient,
    private val naverInfoFeignClient: NaverUserInfoClient,
    private val naverFeignProperties: NaverFeignProperties,
    private val userRepository: UserRepository
) {
    companion object {
        const val GRANT_TYPE = "authorization_code"
    }

    fun getCode(code: String): TokenResponse {
        val naverToken: String = "Bearer " + naverTokenClient.getCode(
            grant_type = GRANT_TYPE,
            client_id = naverFeignProperties.clientId,
            client_secret = naverFeignProperties.clientSecret,
            code = code,
            state = naverFeignProperties.state
        ).accessToken

        val userInfo: NaverUserInfoElement = naverInfoFeignClient.getUserInfo(naverToken).response

        if (!userRepository.existsByEmail(userInfo.email)) {
            val user = User(
                id = UUID(0, 0),
                email = userInfo.email,
                name = userInfo.name,
                level = Level.VEGAN,
                profile = userInfo.profile_image,
                sex = when (userInfo.gender) {
                    "M" -> Sex.M
                    "F" -> Sex.F
                    else -> Sex.U
                }
            )
            userRepository.save(user)
        }

        // TODO 토큰 생성 로직 추가

        return TokenResponse("access_token")
    }
}
