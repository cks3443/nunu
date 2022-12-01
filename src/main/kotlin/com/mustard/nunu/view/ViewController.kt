package com.mustard.nunu.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/view")
class ViewController {


    val VIEW_1 = "view1"

    @GetMapping("/1")
    fun getView1(
        @RequestParam id: Long,
    ): String {

        return VIEW_1
    }
}