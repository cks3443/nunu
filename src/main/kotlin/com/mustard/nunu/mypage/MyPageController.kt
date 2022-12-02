package com.mustard.nunu.mypage

import com.mustard.nunu.post.PostService
import com.mustard.nunu.state.StateEnum
import com.mustard.nunu.user.People
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.text.SimpleDateFormat

@Controller
@RequestMapping("/mypage")
class MyPageController(
    private val postService: PostService,
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
}