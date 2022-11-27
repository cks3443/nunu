package com.mustard.nunu.post

import com.mustard.nunu.user.People
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {


    @Query("select p from Post p where p.people = :people order by p.updateDate desc")
    fun findAllByPeople(@Param("people") people: People): MutableList<Post>
}