package com.mustard.nunu.ai

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PapagoTest {

    @Autowired
    lateinit var papago: Papago

//    @Test
//    fun main_function_test() {
//        val txt = papago.ko2eng_function("안녕하세요")
//        println(txt)
//    }

    @Test
    fun ko2eng_naver_cloud() {
        val txt = papago.ko2eng_naver_cloud("안녕하세요")
        println("ko2eng_naver_cloud: $txt")
    }

    @Test
    fun eng2ko_naver_cloud() {
        val txt = papago.eng2ko_naver_cloud("hello")
        println("eng2ko_naver_cloud: $txt")
    }
}