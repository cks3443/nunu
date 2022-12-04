package com.mustard.nunu.notice

import org.springframework.stereotype.Service

@Service
class NoticeService(
    private val notices: NoticeRepository,
) {

    fun getTheLast10Notices() = notices.findLastTen()
}