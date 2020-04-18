package org.blackhouse.participant.bootstrap

import org.blackhouse.participant.service.ParticipantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/*
    ONLY FOR DEVELOPMENT PURPOSES!
 */
@Component
class DatabaseInitializer {

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @Autowired
    lateinit var service: ParticipantService

    @PostConstruct
    fun initialise() {
        initCollections()
    }

    private fun initCollections() {
        mongoOperations.collectionExists("participant").subscribe {
            if (it != true) mongoOperations.createCollection("participant").subscribe {
                println("Participant collections created!")
            } else println("Participant collections already exist!")
        }
    }
}