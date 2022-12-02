package com.mustard.nunu.view

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/view")
class ViewController {


    val VIEW_1 = "view1"

    val VIEW_2 = "view2"

    @GetMapping("/1/{id}")
    fun getView1(

        @PathVariable id: Long,
        model: Model,
    ): String {

        model["id"] = id

        return VIEW_1
    }

    @GetMapping("/2/{id}")
    fun getView2(

        @PathVariable id: Long,
        model: Model,
    ): String {

        model["id"] = id

        return VIEW_2
    }
}