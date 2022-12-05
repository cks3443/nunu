package com.mustard.nunu.notice

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mustard.nunu.base.Base
import com.mustard.nunu.user.People
import javax.persistence.*

@Entity
class Notice() : Base() {
    @Column(name = "text", length = 1000)
    open var text: String? = null


    @ManyToOne
    @JoinColumn(name = "people_id")
    @JsonIgnore
    open var people: People? = null
}