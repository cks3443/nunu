package com.mustard.nunu.notice

import com.mustard.nunu.base.Base
import com.mustard.nunu.user.People
import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@Entity
open class Notice : Base() {
    @Column(name = "text", length = 5000)
    open var text: String? = null


    @ManyToOne
    @JoinColumn(name = "people_id")
    @JsonIgnore
    open var people: People? = null
}