package com.mustard.nunu.block

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/block")
class BlockController() {

    val BLOCK_EDIT_PAGE = "block_edit"

    @GetMapping("/edit")
    fun getBlockEdit(
        @RequestParam postid: Long,
        model: Model,
    ): String {

        model["postid"] = postid

        return BLOCK_EDIT_PAGE
    }
}