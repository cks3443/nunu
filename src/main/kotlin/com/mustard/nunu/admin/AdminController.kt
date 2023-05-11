package com.mustard.nunu.admin

import com.mustard.nunu.user.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/admin")
class AdminController(
    private val user_service: UserService,
) {
    private val INDEX = "admin/index"
    private val NEW_PASSWORD = "11111"

    @GetMapping("")
    fun adminIndex(
        @RequestParam
        page_num: Int?,
        @RequestParam
        size_page: Int?,
        model: Model,
    ): String {
        var _page_num = 0
        var _size_page = 20
        page_num?.let { _page_num = it }
        size_page?.let { _size_page = it }
        val page_info = PageRequest.of(_page_num, _size_page)
        model["Page"] = user_service.getAllPeople(page_info)
        return INDEX
    }

    @GetMapping("/set/password")
    @ResponseBody
    fun setPassword(
        id: String,
    ): ResponseEntity<Boolean> {
        return try {
            user_service.setPasswdToMember(id, NEW_PASSWORD)
            ResponseEntity.ok().body(true)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(false)
        }
    }
}