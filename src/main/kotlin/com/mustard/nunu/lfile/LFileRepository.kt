package com.mustard.nunu.lfile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LFileRepository : JpaRepository<LFile, String> {
}