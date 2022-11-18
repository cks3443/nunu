package com.mustard.nunu.lfile

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
class LFileController(
    private val storageService: LFileStorageService,
) {

    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<String> {

        var message = ""

        return try {

            val lfile = storageService.store(file)

            message = lfile!!.id!!

            ResponseEntity.ok().body(message)
        } catch (e: Exception) {
            e.printStackTrace()
            message = "Could not upload the file: ${file.originalFilename}"

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message)
        }
    }

    @PostMapping("/muploads")
    fun uploadMutipleFile(
        @RequestParam("files") files: MutableList<MultipartFile>
    ): ResponseEntity<MutableList<String>> {

        return try {

            val lfiles = storageService.multiStore(files)

            val namelist = lfiles.map {

                var file_name = ""

                it?.let { l_it ->
                    file_name = "${l_it.id!!}|${l_it.name}"
                }

                file_name
            }.toMutableList()

            ResponseEntity.ok().body(namelist)
        } catch (e: Exception) {
            e.printStackTrace()

            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(mutableListOf())
        }
    }

    @GetMapping("/files/{id}")
    fun getLFile(
        @PathVariable id: String,
    ): ResponseEntity<ByteArray> {

        val lfile = storageService.getLFile(id)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lfile.name + "\"")
            .body(lfile.data)
    }
}