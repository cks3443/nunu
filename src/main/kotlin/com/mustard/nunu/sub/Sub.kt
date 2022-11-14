package com.mustard.nunu.sub

import com.mustard.nunu.base.Base
import javax.persistence.*

@Entity
class Sub() : Base() {


    @Column(name = "keyword_storage", length = 2000)
    open var keyword_storage: String? = null

    @get:Transient
    @set:Transient
    open var keywords: MutableList<String>
        get() {
            if (keyword_storage == null || keyword_storage == "") {
                return mutableListOf()
            }

            return keyword_storage!!.split(delimiter).toMutableList()
        }
        set(value) {
            keyword_storage = value.joinToString(delimiter)
        }
}