package com.mustard.nunu.lfile

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

@Service
class LFileStorageService(
    private val lfiles: LFileRepository,
) {

    fun store(file: MultipartFile): LFile? {

        return try {

            val fname = StringUtils.cleanPath(file.originalFilename!!)

            val lfile = LFile(fname, file.contentType, file.bytes)

            lfiles.save(lfile)

            lfile
        } catch (e: Exception) {
            e.printStackTrace()

            null
        }
    }

    fun getLFile(id: String): LFile {
        return lfiles.findById(id).get()
    }

}