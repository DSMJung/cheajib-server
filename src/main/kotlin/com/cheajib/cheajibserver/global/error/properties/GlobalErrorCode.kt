package com.cheajib.cheajibserver.global.error.properties

enum class GlobalErrorCode(
    override val errorStatus: Int,
    override val errorMessage: String
) : ErrorProperty {

    METHOD_ARGUMENT_NOT_VALID(400, "Method Argument Not Valid"),
    CONSTRAINT_VIOLATION(400, "Constraint Violation"),
    DATA_INTEGRITY_VIOLATION(400, "DataIntegrity Violation"),
    ILLEGAL_ARGUMENT(400, "Illegal Argument"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
}
