package com.mustard.nunu.login

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController() {

    val LOGIN_PAGE = "login"

    @GetMapping
    fun getLoginPage() = LOGIN_PAGE
}