package com.mustard.nunu.ai

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PapagoTest {

    @Autowired
    lateinit var papago: Papago

    @Test
    fun main_function_test() {
        val txt = papago.eng2ko_function("We are students")
        println(txt)
    }
}