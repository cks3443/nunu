package com.mustard.nunu.security

import com.mustard.nunu.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    private val service: UserService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        http
            ?.csrf()?.disable()
            ?.authorizeHttpRequests()
            ?.antMatchers(
                "/css/**",
                "/assets/**",
                "/js/**",
                "/sign",
                "/login",
                "/login/**"
            )
            ?.permitAll()
            ?.antMatchers(
                "/sign",
                "/sign/**"
            )
            ?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.formLogin()
            ?.loginPage("/login")
            ?.defaultSuccessUrl("/")
            ?.usernameParameter("email")
            ?.and()
            ?.logout()
            ?.logoutUrl("/logout")
            ?.logoutSuccessUrl("/login")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(11)

    override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)

        auth
            ?.userDetailsService(service)
            ?.passwordEncoder(passwordEncoder())

    }

}