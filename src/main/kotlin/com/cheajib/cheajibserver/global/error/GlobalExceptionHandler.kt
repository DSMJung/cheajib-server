package com.cheajib.cheajibserver.global.error

import com.cheajib.cheajibserver.global.error.exception.CustomDataIntegrityViolationException
import com.cheajib.cheajibserver.global.error.exception.CustomIllegalArgumentException
import com.cheajib.cheajibserver.global.error.exception.CustomMethodArgumentNotValidException
import com.cheajib.cheajibserver.global.error.properties.ErrorResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException::class)
    fun handleGlobalException(exception: GlobalException): ResponseEntity<ErrorResponse<Unit>> {
        return handleException(exception)
    }

    /*
        @ModelAttribute 어노테이션으로 받은 파라미터
        Valid로 binding error 발생시 발생
     */
    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<*>? {
        val errorMap: MutableMap<String, String?> = HashMap()

        for (error in e.fieldErrors) {
            errorMap[error.field] = error.defaultMessage
        }
        return ResponseEntity<Map<String, String?>>(errorMap, HttpStatus.BAD_REQUEST)
    }

    /*
        Valid로 binding error 발생시 발생
        @RequestBody 어노테이션으로 받은 파라미터
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(): ResponseEntity<ErrorResponse<Unit>> {
        return handleException(CustomMethodArgumentNotValidException.EXCEPTION)
    }

    /*
        데이터의 삽입/수정이 무결성 제약 조건을 위반할 때 발생하는 예외이다.
     */
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(): ResponseEntity<ErrorResponse<Unit>> {
        return handleException(CustomDataIntegrityViolationException.EXCEPTION)
    }

    /*
        적합하지 않거나(illegal) 적절하지 못한(inappropriate) 인자를 메소드에 넘겨주었을 때 발생합니다.
    */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(): ResponseEntity<ErrorResponse<Unit>> {
        return handleException(CustomIllegalArgumentException.EXCEPTION)
    }

    private fun handleException(e: GlobalException): ResponseEntity<ErrorResponse<Unit>> {
        val status = HttpStatus.valueOf(e.errorStatus)
        val body = ErrorResponse.of(e)
        return ResponseEntity(body, status)
    }
}
