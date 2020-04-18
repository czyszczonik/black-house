package org.blackhouse.participant.router

import org.blackhouse.participant.handler.ParticipantHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val handler: ParticipantHandler) {
    @Bean
    fun customerRoutes() = router {
        "/participants".nest {
            GET("/{id}", handler::findById)
            POST("/{id}", handler::update)
            GET("/", handler::getAll)
            GET("/first/{firstName}", handler::findByFirstName)
            GET("/last/{lastName}", handler::findByLastName)
            POST("/", handler::create)
            DELETE("/{id}", handler::delete)
        }
    }
}

