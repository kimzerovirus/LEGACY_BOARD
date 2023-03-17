package me.kzv.legacyboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LegacyBoardApplication

fun main(args: Array<String>) {
    runApplication<LegacyBoardApplication>(*args)
}
