package com.mustard.nunu.post

import com.mustard.nunu.user.People
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postrepo: PostRepository,
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

        return postrepo.findAllByPeople(people)
    }
}