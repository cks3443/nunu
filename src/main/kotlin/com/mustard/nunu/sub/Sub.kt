package com.mustard.nunu.sub

import com.mustard.nunu.base.Base
import javax.persistence.*

@Entity
class Sub() : Base() {

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

//    @ElementCollection
//    @CollectionTable(name = "sub_keywords", joinColumns = [JoinColumn(name = "owner_id")])
//    @Column(name = "keyword", length = 1000)
//    open var keywords: MutableSet<String> = mutableSetOf()


    @Column(name = "ord")
    open var ord: Int? = null

    @ElementCollection
    @CollectionTable(name = "sub_imgframe", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "imgframe", length = 1000)
    open var imgframe: MutableList<String> = mutableListOf("", "", "")


    @Column(name = "textarea", length = 3000)
    open var textarea: String? = null


    @ElementCollection
    @CollectionTable(name = "sub_ord2", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "ord_2")
    open var ord2: MutableList<Int> = mutableListOf(0, 1)

    @Column(name = "keyword_storage", length = 5000)
    open var keywordStorage: String? = null

    fun addKeyword(v: String) {
        keywords.add(v)

        keywordStorage = keywords.joinToString("|")
    }

    @Transient
    open var keywords: MutableList<String> = mutableListOf()
        get() {
            if (this.keywordStorage == null) {
                return mutableListOf()
            }

            return keywordStorage!!.split("|").toMutableList()
        }


    @Column(name = "htarea")
    open var htarea: Int? = null
}