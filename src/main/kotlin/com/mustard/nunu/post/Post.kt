package com.mustard.nunu.post

import com.mustard.nunu.base.Base
import com.mustard.nunu.state.StateEnum
import com.mustard.nunu.sub.Sub
import javax.persistence.*

@Entity
class Post() : Base() {

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "post_id")
    open var subs: MutableList<Sub> = mutableListOf()

    @Column(name = "title")
    open var title: String? = null


    @Enumerated(EnumType.STRING)
    @Column(name = "state_enum")
    open var state: StateEnum? = null
}