package com.mustard.nunu.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.mustard.nunu.base.Base
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Email
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
open class People(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "varchar(36)")
    open var id: String = "",

    @Column(unique = true, nullable = false)
    @NotNull
    @Email
    open var email: String = "",

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    open var pwd: String = "",

    open var firstName: String = "",

    open var lastName: String = "",

    open var roles: String = "",
    open var enabled: Boolean = true,

    open var accountNonExpired: Boolean = true,
    open var accountNonLocked: Boolean = true,
    open var credentialsNonExpired: Boolean = true,
    @CreationTimestamp
    open var created: Date = Date(),
    @UpdateTimestamp
    open var modified: Date = Date(),


    ) : UserDetails, OAuth2User, Serializable {

    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        true,
        true,
        true,
        true,
        Date(),
        Date()
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

        val authorities = mutableListOf<GrantedAuthority>()

        roles.split(",")
            .forEach {
                authorities.add(SimpleGrantedAuthority(it.trim()))
            }

        return authorities
    }

    override fun isEnabled() = enabled

    override fun getUsername() = email

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = pwd

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked

    //OAuth2
    override fun getAttributes(): Map<String?, Any?>? {
        return attributes
    }

    override fun getName(): String = email

}