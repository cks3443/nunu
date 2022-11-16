package com.mustard.nunu.post

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/posts")
class PostController {

    val CREATE_PAGE = "create"

    @GetMapping(value = ["/edit", "/edit/{id}"])
    fun getPosts(
        @PathVariable id: Long?,
        model: Model,
    ): String {

        var pid = -1L

        id?.let { pid = it }

        model["id"] = pid

        return CREATE_PAGE
    }
}