package com.mustard.nunu.user

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface UserRepository : Repository<People, String> {

    @Query("SELECT it FROM People it WHERE it.email = :email")
    fun findByEmail(@Param("email") email: String): People?

    fun save(people: People): People

    fun deleteById(id: String)
}