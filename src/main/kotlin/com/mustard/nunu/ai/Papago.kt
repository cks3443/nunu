package com.mustard.nunu.ai

import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


//{
//    "message": {
//    "result": {
//    "srcLangType": "ko",
//    "tarLangType": "en",
//    "translatedText": "Tools should be comfortable and free. Because it is to draw thoughts immediately.",
//    "engineType": "UNDEF_MULTI_SENTENCE",
//    "pivot": null,
//    "dict": null,
//    "tarDict": null
//},
//    "@type": "response",
//    "@service": "naverservice.nmt.proxy",
//    "@version": "1.0.0"
//}
//}
class Result {
    var translatedText: String = ""
}

class Message {
    val result: Result? = null
}

class _Response {
    val message: Message? = null
}

@Service
class Papago(
    private val gson: Gson,
    @Value("\${papago.client.id}")
    private val client_id: String,
    @Value("\${papago.secret.code}")
    private val secret_code: String,
) {
    fun ko2eng_function(txt: String): String {
        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "source=ko&target=en&text=${txt}")
        val response = request_function(body)
        val message_gson = gson.fromJson(response.body?.string(), _Response::class.java)
        println("message_gson: ${message_gson.message?.result}")
        return message_gson.message?.result?.translatedText ?: ""
    }

    fun eng2ko_function(txt: String): String {
        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "source=en&target=ko&text=${txt}")
        val response = request_function(body)
        val message_gson = gson.fromJson(response.body?.string(), _Response::class.java)
        return message_gson.message?.result?.translatedText ?: ""
    }


    protected fun request_function(body: RequestBody): Response {
        val client = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url("https://openapi.naver.com/v1/papago/n2mt").method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("X-Naver-Client-Id", "uHTH9RG2pco2a23JXzdb").addHeader("X-Naver-Client-Secret", "zuJzeTO1ba")
            .build()
        val response = client.newCall(request).execute()
        return response
    }

    protected fun naver_cloud_request_function(body: RequestBody): Response {
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url("https://naveropenapi.apigw.ntruss.com/nmt/v1/translation")
            .method("POST", body)
            .addHeader("X-NCP-APIGW-API-KEY-ID", client_id)
            .addHeader("X-NCP-APIGW-API-KEY", secret_code)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        val response = client.newCall(request).execute()
        return response
    }

    fun ko2eng_naver_cloud(txt: String): String {
        return try {
            val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            val body: RequestBody = RequestBody.create(mediaType, "source=ko&target=en&text=${txt}")
            val response = request_function(body)
            val message_gson = gson.fromJson(response.body?.string(), _Response::class.java)
            message_gson.message?.result?.translatedText ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun eng2ko_naver_cloud(txt: String): String {
        return try {
            val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            val body: RequestBody = RequestBody.create(mediaType, "source=en&target=ko&text=${txt}")
            val response = request_function(body)
            val message_gson = gson.fromJson(response.body?.string(), _Response::class.java)
            message_gson.message?.result?.translatedText ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
