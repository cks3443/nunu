package com.mustard.nunu.user

import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class UserService(
    private val users: UserRepository,
    private val jusers: UserCrudRepository,
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

    fun encodePassword(passwd: String) = encoder.encode(passwd)

    fun setPasswdToMember(
        id: String,
        passwd: String,
    ) {
        val member = jusers.findById(id).get()
        member?.pwd = encodePassword(passwd)
        users.save(member!!)
    }

    @Transactional(readOnly = true)
    fun getAllPeople(pageable: Pageable) = jusers.findAll(pageable)
}