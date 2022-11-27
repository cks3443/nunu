package com.mustard.nunu.main

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/main")
class MainController {

    val MAIN_PAGE = "main"

    val MAIN_BLOCK_PAGE = "mainblock_mb"

    val MAIN_LIST_PAGE = "mainlist_mb"

    @GetMapping(value = [""])
    fun getMainPage(
        @RequestParam("state") state: String?,
        model: Model
    ): String {

        var _state = "CONTINUE"

        state?.let {
            _state = it
        }

        model["state"] = _state

        return MAIN_PAGE
    }
}