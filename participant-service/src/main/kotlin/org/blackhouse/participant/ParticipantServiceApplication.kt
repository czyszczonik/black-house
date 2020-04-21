package org.blackhouse.participant
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParticipantServiceApplication

fun main(args: Array<String>) {
    runApplication<ParticipantServiceApplication>(*args)
}

