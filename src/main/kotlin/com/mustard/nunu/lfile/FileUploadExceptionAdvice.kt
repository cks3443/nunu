package com.mustard.nunu.lfile

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class FileUploadExceptionAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(
        exc: MaxUploadSizeExceededException,
    ): ResponseEntity<String> {

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!")
    }
}