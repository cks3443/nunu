package com.mustard.nunu.ai

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.IOException
import kotlin.jvm.Throws

@Controller
@RequestMapping("/ai")
class AiController(
    private val novelAi: NovelAi,
    private val papago: Papago,
) {

    @PostMapping("/generate")
    @ResponseBody
    @Throws(IOException::class)
    fun generateSentence(
        txt: String,
    ): String {
        val kr_en = papago.ko2eng_naver_cloud(txt)
        val token = novelAi.getAccessToken()
        val en_gen = novelAi.translate(token, kr_en)
        val en_kr = papago.eng2ko_naver_cloud(en_gen)
        return en_kr
    }
}