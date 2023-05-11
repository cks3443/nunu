package com.mustard.nunu.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface UserCrudRepository : CrudRepository<People, String> {
    fun findAll(pageable: Pageable): Page<People>
}