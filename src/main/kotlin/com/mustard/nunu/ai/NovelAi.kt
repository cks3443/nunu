package com.mustard.nunu.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import org.springframework.stereotype.Service


@Service
class NovelAi {

    val accessKey = "nbpHUulGCDP3b8U7XJxkzCVnIBLL0dy2dF7sgPBaTqRNNwTfPIM92W7dsC3f9_HR"

//    val accessKey = "QiPYvge-rZq4kXTMyysMl8sRo3uqoAToUONa2F1E0eTlEsRdsdLjhkLkRMvmRam-"

    fun getAccessToken(): String {
        val client = OkHttpClient().newBuilder()
            .build()
        val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
        val body: RequestBody = RequestBody.create(
            mediaType,
            "{\r\n  \"key\": \"${accessKey}\"\r\n}"
        )
        val request: Request = Request.Builder()
            .url("https://api.novelai.net/user/login")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build()
        val response: Response = client.newCall(request).execute()

        val token = JSONObject(response.body!!.string()).get("accessToken").toString().trim()

        return token
    }

    fun translate(
        token: String,
        origin_txt: String
    ): String {
        val client = OkHttpClient().newBuilder()
            .build()
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = RequestBody.create(
            mediaType,
            "{\r\n  \"input\": \"${origin_txt}\",\r\n  \"model\": \"euterpe-v2\",\r\n  \"parameters\": {\r\n    \"use_string\": true,\r\n    \"temperature\": 1,\r\n    \"min_length\": 10,\r\n    \"max_length\": 30\r\n  }\r\n}"
        )
        val request: Request = Request.Builder()
            .url("https://api.novelai.net/ai/generate")
            .method("POST", body)
            .addHeader(
                "Authorization",
                "Bearer ${token}"
            )
            .addHeader("Content-Type", "application/json")
            .build()
        val response = client.newCall(request).execute()
        val output = JSONObject(response.body!!.string()).get("output")
        return output.toString().trim()
    }
}