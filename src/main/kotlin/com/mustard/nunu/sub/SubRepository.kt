package com.mustard.nunu.sub

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubRepository : JpaRepository<Sub, Long> {
}