package com.mustard.nunu.user

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(
    private val users: UserRepository,
) : UserDetailsService {

    val encoder = BCryptPasswordEncoder(11)

    override fun loadUserByUsername(email: String?): People? {

        return users.findByEmail(email!!) ?: throw RuntimeException("User not found: $email")
    }

    fun saveMember(user: UserDTO): People {

        val member = Member()

        member.email = user.email
        member.firstName = user.firstName
        member.lastName = user.lastName
        member.pwd = encoder.encode(user.password)
        member.roles = "MEMBER"

        return users.save(member)
    }

    fun deleteUser(id: String) = users.deleteById(id)
}