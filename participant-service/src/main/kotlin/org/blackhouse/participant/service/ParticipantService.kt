package org.blackhouse.participant.service

import com.mongodb.client.result.DeleteResult
import org.blackhouse.participant.model.Participant
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ParticipantService {

    fun findById(id: String): Mono<Participant>
    fun getAll():Flux<Participant>
    fun createParticipant(participant: Mono<Participant>): Mono<Participant>
    fun findByNames(firstName: String, lastName:String): Flux<Participant>
    fun findParticipantsByFirstName(firstName: String): Flux<Participant>
    fun findParticipantsByLastName(lastName: String): Flux<Participant>
    fun updateParticipant(participant: Mono<Participant>): Mono<Participant>
    fun deleteParticipant(id: String): Mono<DeleteResult>
}
