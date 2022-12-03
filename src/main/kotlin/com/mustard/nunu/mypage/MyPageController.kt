package com.mustard.nunu.mypage

import com.mustard.nunu.post.PostService
import com.mustard.nunu.state.StateEnum
import com.mustard.nunu.user.People
import com.mustard.nunu.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.text.SimpleDateFormat

@Controller
@RequestMapping("/mypage")
class MyPageController(
        private val postService: PostService,
        private val userService: UserService,
) {

    val MY_PAGE = "mypage"

    @GetMapping
    fun getMyPage(
            @AuthenticationPrincipal user: People,
            model: Model,
    ): String {

        model["id"] = user.email
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        val thedayjoined = formatter.format(user.created)

        model["thedayjoined"] = thedayjoined

        val my_all_posts = postService.getAllMyPosts(user)

        val my_all_done_posts = my_all_posts.filter {
            it.state == StateEnum.DONE
        }

        model["num_done"] = my_all_done_posts.count()

        return MY_PAGE
    }

    @PostMapping("/passwd")
    fun setPasswd(
            @RequestParam id: String,
            @RequestParam passwd: String,
    ): ResponseEntity<String> {

        return try {
            userService.setPasswdToMember(id, passwd)
            ResponseEntity.ok().body("ok")
        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("fail")
        }
    }
}