package com.mustard.nunu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NunuApplication

fun main(args: Array<String>) {
  runApplication<NunuApplication>(*args)
}
