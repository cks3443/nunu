package com.mustard.nunu.post

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mustard.nunu.base.Base
import com.mustard.nunu.state.StateEnum
import com.mustard.nunu.sub.Sub
import com.mustard.nunu.user.People
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


    @ElementCollection
    @CollectionTable(name = "post_ord", joinColumns = [JoinColumn(name = "owner_id")])
    @Column(name = "ord")
    open var ord: MutableList<Int> = mutableListOf(0)

    @ManyToOne
    @JoinColumn(name = "people_id")
    @JsonIgnore
    open var people: People? = null

    override fun toString(): String {

        return """
            subs: ${subs}
            title: ${title}
            state: ${state}
            ord: ${ord}
            people: ${people}
        """.trimIndent()
    }
}