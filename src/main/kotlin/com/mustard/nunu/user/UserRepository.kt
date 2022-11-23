package com.mustard.nunu.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, String> {

    fun findOneByEmail(email: String): User?
}