package com.mustard.nunu.security

import com.mustard.nunu.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    private val service: UserService,
    private val unauthorizedHandler: AuthenticationEntryPoint,
    private val successHandler: WebSecurityAuthSuccessHandler,

    ) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        http
            ?.csrf()?.disable()
            ?.exceptionHandling()
            ?.authenticationEntryPoint(unauthorizedHandler)
            ?.and()
            ?.authorizeHttpRequests()
            ?.antMatchers("/css/**", "/assets/**", "/js/**", "/sign", "/login", "/login/**")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.formLogin()
            ?.loginPage("/login")
            ?.defaultSuccessUrl("/")
            ?.usernameParameter("email")
            ?.successHandler(successHandler)
            ?.failureHandler(SimpleUrlAuthenticationFailureHandler())
            ?.and()
            ?.logout()
            ?.logoutUrl("/logout")
            ?.logoutSuccessUrl("/")

    }
}