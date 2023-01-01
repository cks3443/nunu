package com.mustard.nunu.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.springframework.stereotype.Service


@Service
class Papago {
    fun ko2eng_function(txt: String) {
        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "source=ko&target=en&text=${txt}")
        val response = request_function(body)
        println(response.body!!.string())
    }

    fun eng2ko_function(txt: String) {
        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "source=en&target=ko&text=${txt}")
        val response = request_function(body)
        println(response.body!!.string())
    }

    protected fun request_function(body: RequestBody): Response {
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url("https://openapi.naver.com/v1/papago/n2mt")
            .method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("X-Naver-Client-Id", "uHTH9RG2pco2a23JXzdb")
            .addHeader("X-Naver-Client-Secret", "zuJzeTO1ba")
            .build()
        val response = client.newCall(request).execute()
        return response
    }
}
