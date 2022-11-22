package com.mustard.nunu.sub

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/subs")
class SubRestController(
    private val subService: SubService,
) {


    @PostMapping(value = ["", "/{id}"])
    fun getSub(
        id: Long?,
    ): ResponseEntity<Sub?> {

        return try {

            var sub: Sub? = null

            id?.let {
                sub = subService.getSubById(it)
            } ?: run {

                sub = Sub()

                sub!!.heading = ""

                sub!!.addKeyword("")
            }

            ResponseEntity.ok().body(sub)
        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null)
        }
    }

}