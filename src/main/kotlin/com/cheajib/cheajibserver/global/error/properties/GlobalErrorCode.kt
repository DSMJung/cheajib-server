package com.cheajib.cheajibserver.global.error.properties

enum class GlobalErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {

    COMMENT_NOT_FOUND(404, "Comment Not Found"),

    METHOD_ARGUMENT_NOT_VALID(400, "Method Argument Not Valid"),
    CONSTRAINT_VIOLATION(400, "Constraint Violation"),
    DATA_INTEGRITY_VIOLATION(400, "DataIntegrity Violation"),
    ILLEGAL_ARGUMENT(400, "Illegal Argument"),

    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),
    FEIGN_EXPIRED_TOKEN(419, "Feign Expired Token"),
    FEIGN_FORBIDDEN(403, "Feign Forbidden"),
    FEIGN_UN_AUTHORIZED(401, "Feign Unauthorized"),

    IMAGE_NOT_FOUND(404, "Image Not Found"),
    INVALID_EXTENSION_FORMAT(401, "Invalid Extension Format"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
}
