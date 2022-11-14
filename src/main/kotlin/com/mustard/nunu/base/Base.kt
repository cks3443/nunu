package com.mustard.nunu.base

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import java.io.Serializable
import java.util.Date
import javax.persistence.*

@MappedSuperclass
open class Base : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    open var createdDate: Date? = null


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    open var updateDate: Date? = null


    @Column(name = "active")
    open var active: Boolean? = null

    @Transient
    open var delimiter: String = ","
}