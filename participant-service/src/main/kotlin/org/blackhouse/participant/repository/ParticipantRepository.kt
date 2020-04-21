package org.blackhouse.participant.repository


import com.mongodb.client.result.DeleteResult
import org.blackhouse.participant.model.Participant
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ParticipantRepository(private val template: ReactiveMongoTemplate) {

    fun create(participant: Mono<Participant>): Mono<Participant> = template.save(participant)
    fun findById(id: String) = template.findById<Participant>(id)
    fun findByNames(firstName: String, lastName: String): Flux<Participant> =
            template.find(
                    Query(where("firstName").regex(".*$firstName.*")
                            .and("lastName").regex(".*$lastName.*")))

    fun findAll(): Flux<Participant> = template.findAll()
    fun update(participant: Mono<Participant>): Mono<Participant> = template.save(participant)
    fun delete(participant: Mono<Participant>): Mono<DeleteResult> = template.remove(participant)

}
