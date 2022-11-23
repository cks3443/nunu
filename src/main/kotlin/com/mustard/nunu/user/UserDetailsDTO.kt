package com.mustard.nunu.user

import java.util.*

data class UserDetailsDTO(

    val id: String,
    var email: String,
    var pwd: String,
    var firstName: String,
    var lastName: String,
    var roles: String,
    var enabled: Boolean,
    var accountNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var credentialsNonExpired: Boolean,
    var created: Date,
    var modified: Date,
)
