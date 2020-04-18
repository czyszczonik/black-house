package org.blackhouse.participant.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.blackhouse.participant.model.Participant
import org.blackhouse.participant.repository.ParticipantRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

internal class ParticipantServiceImplTest {

    private val repository = mockk<ParticipantRepository>()
    private val participantService = ParticipantServiceImpl(repository)

    private val id: String = "id"
    private val firstName: String = "first"
    private val lastName: String = "last"
    private val age: Int = 18
    private val phone: String = "phone"
    private val mail: String = "mail"
    private val participant: Participant = Participant(id, firstName, lastName, age, mail, phone)
    private val participantCreateEntity: Mono<Participant> = Participant(firstName, lastName, age, mail, phone).toMono()


    @Test
    fun findById() {
        every { repository.findById(id) } returns participant.toMono()

        val result = participantService.findById(id)

        assertEquals(participant, result.block())

    }

    @Test
    fun createParticipant() {
        every { repository.create(participantCreateEntity) } returns participant.toMono()

        val result = participantService.createParticipant(participantCreateEntity)

        assertEquals(participant, result.block())
        verify(exactly = 1) {
            repository.create(participantCreateEntity)
        }

    }

    @Test
    fun findByNames() {
        val arrayOfParticipants = arrayOf(participant)
        every { repository.findByNames(firstName, lastName) } returns Flux.fromArray(arrayOfParticipants)

        val result = participantService.findByNames(firstName, lastName)

        assertEquals(participant, result.blockFirst())
    }

    @Test
    fun getAll() {
        val arrayOfParticipants = arrayOf(participant)
        every { repository.findAll() } returns Flux.fromArray(arrayOfParticipants)

        val result = participantService.getAll()

        assertEquals(participant, result.blockFirst())
    }

    @Test
    fun searchParticipantsByFirstName() {
        val arrayOfParticipants = arrayOf(participant)
        every { repository.findByNames(firstName, "") } returns Flux.fromArray(arrayOfParticipants)

        val result = participantService.findParticipantsByFirstName(firstName)

        assertEquals(participant, result.blockFirst())
    }

    @Test
    fun searchParticipantsByLastName() {
        val arrayOfParticipants = arrayOf(participant)
        every { repository.findByNames("", lastName) } returns Flux.fromArray(arrayOfParticipants)

        val result = participantService.findParticipantsByLastName(lastName)

        assertEquals(participant, result.blockFirst())
    }
}