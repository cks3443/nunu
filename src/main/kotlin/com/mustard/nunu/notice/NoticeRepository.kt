package com.mustard.nunu.notice

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface NoticeRepository : JpaRepository<Notice, Long> {

    @Query(
        value = "select * from Notice order by id desc limit 10",
        nativeQuery = true
    )
    fun findLastTen(): MutableList<Notice>
}