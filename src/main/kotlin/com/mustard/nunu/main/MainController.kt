package com.mustard.nunu.main

import org.springframework.stereotype.Controller
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
    fun getMainPage(@RequestParam("block") pageid: Int?): String {

        var PAGE = MAIN_PAGE

        pageid?.let {

            when (it) {
                2 -> {
                    PAGE = MAIN_BLOCK_PAGE
                }

                3 -> {
                    PAGE = MAIN_LIST_PAGE
                }

                else -> {
                }
            }
        }

        return PAGE
    }
}