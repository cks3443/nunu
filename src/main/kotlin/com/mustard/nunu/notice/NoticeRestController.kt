package com.mustard.nunu.notice

import com.google.gson.Gson
import com.mustard.nunu.user.People
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/notices")
class NoticeRestController(
    private val notices: NoticeRepository,
    private val gson: Gson,
) {

    @GetMapping
    @Throws(Exception::class)
    fun getNotice() = Notice()

    @GetMapping(value = ["/{id}"])
    @Throws(Exception::class)
    fun getNoticeById(@PathVariable id: Long) = notices.findById(id).get()


    @PostMapping
    @Throws(Exception::class)
    fun saveNotice(@RequestBody notice: Notice)= notices.save(notice)

    @PutMapping("/{id}")
    @Throws(Exception::class)
    fun updateNotice(
        @PathVariable id: Long,
        @RequestBody notice: Notice,
        @AuthenticationPrincipal user: People,
    ): Notice {
        notice.people = user
        return notices.save(notice)
    }
}