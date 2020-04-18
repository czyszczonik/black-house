package org.blackhouse.participant.handler

import org.blackhouse.participant.model.Participant
import org.blackhouse.participant.service.ParticipantService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI

@Component
class ParticipantHandler(private val service: ParticipantService) {


    fun getAll(request: ServerRequest): Mono<ServerResponse> =
            service.getAll()
                    .collectList()
                    .flatMap { ok().body(fromValue(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun findById(request: ServerRequest): Mono<ServerResponse> =
            service.findById(request.pathVariable("id"))
                    .flatMap { ok().body(fromValue(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun create(request: ServerRequest): Mono<ServerResponse> =
            service.createParticipant(request.bodyToMono()).flatMap {
                created(URI.create("/participant/${it.id}")).build()
            }

    fun delete(request: ServerRequest): Mono<ServerResponse> =
            service.deleteParticipant(request.pathVariable("id"))
                    .flatMap {
                        when {
                            it.deletedCount != 0L -> ok().build()
                            else -> status(HttpStatus.NOT_FOUND).build()
                        }
                    }

    fun findByFirstName(request: ServerRequest): Mono<ServerResponse> =
            service.findParticipantsByFirstName(
                    request.pathVariable("firstName"))
                    .collectList()
                    .flatMap { ok().body(fromValue(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun findByLastName(request: ServerRequest): Mono<ServerResponse> =
            service.findParticipantsByLastName(
                    request.pathVariable("lastName"))
                    .collectList()
                    .flatMap { ok().body(fromValue(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun update(request: ServerRequest): Mono<ServerResponse> =
            service.updateParticipant(
                    request.bodyToMono(Participant::class.java))
                    .flatMap { ok().body(fromValue(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

}