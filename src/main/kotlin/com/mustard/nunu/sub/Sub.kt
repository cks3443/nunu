package com.mustard.nunu.sub

import com.mustard.nunu.base.Base
import javax.persistence.*

@Entity
class Sub() : Base() {


    @Column(name = "keyword_storage", length = 2000)
    open var keyword_storage: String? = null

//    @Transient
//    open var keywords: MutableList<String> = mutableListOf()
//        get() {
//            if (keyword_storage == null) {
//                return mutableListOf()
//            }
//
//            return keyword_storage!!.split(delimiter).toMutableList()
//        }


    @Transient
    open var showControlPanel: Boolean = false

    @Transient
    open var showFileUploadPan: Boolean = false

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sub_images", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "image")
    open var images: MutableList<String> = mutableListOf()


    @ElementCollection
    @CollectionTable(name = "sub_files", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "file", length = 500)
    open var files: MutableList<String> = mutableListOf()


    @Column(name = "heading", length = 1000)
    open var heading: String? = null

    @ElementCollection
    @CollectionTable(name = "sub_keywords", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "keyword", length = 1000)
    open var keywords: MutableSet<String> = mutableSetOf()
}