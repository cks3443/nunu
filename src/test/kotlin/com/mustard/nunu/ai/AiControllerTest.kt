package com.mustard.nunu.ai

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
class AiControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun generateSentence() {
        mockMvc.perform(post("/generate?txt=안녕하세요"))
            .andDo(print())
    }
}