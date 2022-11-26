package com.mustard.nunu.auth

import com.mustard.nunu.user.Member
import com.mustard.nunu.user.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@Service
class CustomOAuth2UserService(
    private val members: UserRepository,
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @ExceptionHandler(value = [OAuth2AuthenticationException::class])
    fun exceptionHandler(e: Exception) {
        e.printStackTrace()
    }

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        val registrationId = userRequest?.clientRegistration?.registrationId
        val userNameAttributeName =
            userRequest?.clientRegistration?.providerDetails?.userInfoEndpoint?.userNameAttributeName
        val attributes = oAuth2User.attributes

        var name: String? = null
        var email: String? = null
        var picture: String? = null

        when (registrationId) {
            "kakao" -> {
                val properties = attributes.get("properties") as Map<String, Any>
                val kakao_account = attributes.get("kakao_account") as Map<String, Any>

                name = properties["nickname"] as String

                if (kakao_account["has_email"] as Boolean) {

                    email = kakao_account["email"] as String
                }

                picture = properties["profile_image"] as String


            }

            "google" -> {
                name = attributes.get("name") as String?
                email = attributes.get("email") as String?
                picture = attributes.get("picture") as String?
            }
        }

        email?.let {

            var user = members.findByEmail(it)

            when (user) {
                null -> {

                    user = Member(name!!, email, "MEMBER")
                }

                else -> {
                    user?.firstName = name!!
                }
            }

            members.save(user!!)
        }


        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority("MEMBER")),
            attributes,
            userNameAttributeName
        )
    }
}