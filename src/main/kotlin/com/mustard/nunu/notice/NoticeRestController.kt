package com.mustard.nunu.notice

import com.mustard.nunu.user.People
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notices")
class NoticeRestController(
    private val notices: NoticeRepository,
) {

    @GetMapping(value = ["", "/{id}"])
    fun getNoticeById(
        id: Long?,
    ): ResponseEntity<Notice?> {

        println("getNoticeById Function Running")
        return try {
            var notice: Notice? = null

            id?.let {
                notice = notices.findById(it).get()
            } ?: run {
                notice = Notice()
            }

            ResponseEntity.ok().body(notice)
        } catch (e: RuntimeException) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null)
        }

    }

    @PostMapping
    @Throws(Exception::class)
    fun saveNotice(@RequestBody notice: Notice): Notice {
        return notices.save(notice)
    }

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