package org.blackhouse.participant.service

import com.mongodb.client.result.DeleteResult
import org.blackhouse.participant.model.Participant
import org.blackhouse.participant.repository.ParticipantRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ParticipantServiceImpl(private val repository: ParticipantRepository) : ParticipantService {


    override fun getAll(): Flux<Participant> = repository.findAll()

    override fun findById(id: String): Mono<Participant> = repository.findById(id)

    override fun createParticipant(participant: Mono<Participant>): Mono<Participant> = repository.create(participant)

    override fun deleteParticipant(id: String): Mono<DeleteResult> = repository.findById(id).let { repository.delete(it) }

    override fun findParticipantsByFirstName(firstName: String): Flux<Participant> =
            repository.findByNames(firstName,"")

    override fun findParticipantsByLastName(lastName: String): Flux<Participant> =
            repository.findByNames("",lastName)

    override fun updateParticipant(participant: Mono<Participant>): Mono<Participant> = repository.update(participant)

    override fun findByNames(firstName: String, lastName: String): Flux<Participant> = repository.findByNames(firstName, lastName)
}
