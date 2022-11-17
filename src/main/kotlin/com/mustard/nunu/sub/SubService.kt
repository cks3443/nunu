package com.mustard.nunu.sub

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubService(
    private val subs: SubRepository,
) {

    @Transactional(readOnly = true)
    fun getSubById(id: Long) = subs.findById(id).get()
}