package com.cheajib.cheajibserver.global.security.jwt.exception.properties

import com.cheajib.cheajibserver.global.error.properties.ErrorProperty

enum class JwtErrorCode(

    override val errorStatus: Int,
    override val errorMessage: String

) : ErrorProperty {

    JWT_VALIDATE_FAILED(401, "Token Validate Failed"),
    JWT_EXPIRED(401, "Jwt Token Expired"),
    JWT_SIGNATURE(401, "Invalid Signature"),
    UNEXPECTED_TOKEN(500, "Unexpected Token Exception")
}
