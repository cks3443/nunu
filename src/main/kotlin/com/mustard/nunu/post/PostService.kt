package com.mustard.nunu.post

import com.google.gson.Gson
import com.mustard.nunu.sub.Sub
import com.mustard.nunu.user.People
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postes: PostRepository,
    private val gson: Gson,
) {


    fun setAllKeywordStorages(post: Post) {

//        post.subs.forEach { sub ->
//
//            println(sub.keywords.toString())
//            sub.keyword_storage = sub.keywords.joinToString(Sub::delimiter.toString())
//
//            println(sub.keyword_storage)
//        }
    }


    fun getAllMyPosts(
        people: People,
    ): MutableList<Post> {

        return postes.findAllByPeople(people)
    }

    fun setActiveOfPost(
        id: Long,
        active: Boolean,
    ) {
        val post_by_id = postes.findById(id).get()
        post_by_id.active = active

        postes.save(post_by_id)
    }

    @Transactional
    fun addSubToPost(
        post_str: String,
        user: People,
    ): Post? {
        return try {
            val post = gson.fromJson(post_str, Post::class.java)
            post.people = user
            val sub = Sub().apply {
                heading = ""
                addKeyword("")
            }
            post?.subs?.add(sub)
            val sub_index = post?.subs?.size?.minus(1)
            sub_index?.let {
                post?.ord?.add(it)
            } ?: run {
                throw Exception("sub_index is null")
            }
            postes.save(post)
            post
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}