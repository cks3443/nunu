package com.mustard.nunu.sign

import com.mustard.nunu.user.UserDTO
import com.mustard.nunu.user.UserRepository
import com.mustard.nunu.user.UserService
import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.StringJoiner
import javax.validation.Valid
import kotlin.math.sign

@Controller
@RequestMapping("/sign")
class SignInController(
    private val users: UserRepository,
    private val userService: UserService,
) {

    val SIGN_PAGE = "sign"

    @GetMapping
    fun getSignPage(
        model: Model,
    ): String {

        model["signdto"] = SignDTO()

        return SIGN_PAGE
    }

    @PostMapping
    fun postSign(
        @Valid signdto: SignDTO,

        result: BindingResult,

        model: Model,
    ): String {

        println(signdto)

        val user = users.findByEmail(signdto.email)

        return when {
            result.hasErrors() -> {
                "redirect:/${SIGN_PAGE}"
            }

            (signdto.passwd1 != signdto.passwd2) -> {
                result.rejectValue("passwd", "비밀번호 이상")
                return "redirect:/${SIGN_PAGE}"
            }

            user != null -> {
                result.rejectValue("email", "이메일이 이미 있습니다.")
                return "redirect:/${SIGN_PAGE}"

            }

            else -> {

                val userdto = UserDTO("", "", "", "")

                userdto.email = signdto.email
                userdto.password = signdto.passwd1

                userService.saveMember(userdto)

                "redirect:/login"
            }
        }

    }

}