package com.mustard.nunu.lfile

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
class LFile() {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    open var id: String? = null

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    open var createdDate: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    open var updatedDate: Date? = null

    @Column(name = "name")
    open var name: String? = null

    @Column(name = "type")
    open var type: String? = null

    @Lob
    @Column(name = "data")
    open var data: ByteArray? = null

    constructor(name: String?, type: String?, data: ByteArray) : this() {
        this.name = name
        this.type = type
        this.data = data
    }
}