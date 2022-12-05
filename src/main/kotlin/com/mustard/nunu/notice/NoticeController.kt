package com.mustard.nunu.notice

import com.mustard.nunu.user.People
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {

    val NOTICE_PAGE = "notice"

    val NOTICE_EDIT_PAGE = "notice_edit"

    @GetMapping
    fun getNoticePage(
        @AuthenticationPrincipal user: People,
        model: Model,
    ): String {

        model["notices"] = noticeService.getTheLast10Notices()

        return NOTICE_PAGE
    }

    @GetMapping(value = ["/edit", "/edit/{id}"])
    fun getNoticeEditPage(
        @PathVariable id: Long?,
        model: Model,
    ): String {

        id?.let { model["id"] = it }

        return NOTICE_EDIT_PAGE
    }


}