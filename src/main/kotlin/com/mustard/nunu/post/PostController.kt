package com.mustard.nunu.post

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/posts")
class PostController(
    private val post_service: PostService,
) {

    val CREATE_PAGE = "create"

    val CREATE_PAGE_2 = "create2"

    @GetMapping(value = ["/edit", "/edit/{id}"])
    fun getPosts(
        @PathVariable id: Long?,
        model: Model,
    ): String {

        var pid = -1L

        id?.let { pid = it }

        model["id"] = pid

        return CREATE_PAGE_2
    }

    @PostMapping("/{id}/disable")
    @ResponseBody
    fun disablePost(
        @PathVariable id: Long,
    ): ResponseEntity<Boolean> {
        return try {
            post_service.setActiveOfPost(id, false)
            ResponseEntity.ok().body(true)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(false)
        }
    }
}