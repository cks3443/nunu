package com.mustard.nunu.post

import com.google.gson.Gson
import com.mustard.nunu.sub.Sub
import com.mustard.nunu.user.People
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.LongSummaryStatistics

@RestController
@RequestMapping("/api/v1/posts")
class PostRestController(
    private val postes: PostRepository,
    private val gson: Gson,
    private val postService: PostService,
) {


    @PostMapping(value = ["", "/{id}"])
    fun postPosts(
        @PathVariable id: Long?,
    ): ResponseEntity<Post?> {

        return try {

            var post: Post? = null

            id?.let {
                post = postes.findById(it).get()
            } ?: run {

                post = Post()
                post!!.title = ""
                val sub = Sub()
                sub.heading = ""
                sub.addKeyword("")

                post!!.subs.add(sub)

            }

            ResponseEntity.ok().body(post)

        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null)
        }
    }

    @PostMapping("/save")
    fun savePost(
        @RequestParam post_str: String,
        @AuthenticationPrincipal user: People,
    ): ResponseEntity<Post?> {

        return try {
            val post = gson.fromJson(post_str, Post::class.java)
            post.people = user
            postes.save(post)

            ResponseEntity.ok().body(post)
        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null)
        }
    }

    @PostMapping("/all")
    fun getAllPosts(
        @AuthenticationPrincipal people: People,
    ): ResponseEntity<MutableList<Post>> {

        return try {

            val posts_all = postService.getAllMyPosts(people)
            ResponseEntity.ok().body(posts_all)

        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(mutableListOf())
        }
    }
}