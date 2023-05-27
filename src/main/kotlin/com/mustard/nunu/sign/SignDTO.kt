package com.mustard.nunu.sign

import org.springframework.stereotype.Component
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Component
data class SignDTO(
    @Email
    @NotBlank
    @NotNull
    var email: String = "",
    @NotBlank
    @NotNull
    @Size(min = 8, max = 15)
    var passwd1: String = "",
    @NotBlank
    @NotNull
    @Size(min = 8, max = 15)
    var passwd2: String = "",
)
