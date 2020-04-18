//package org.blackhouse.participant.utils
//
//import mu.KLogger
//
//fun <A> KLogger.aroundExecution(
//        operationName: String,
//        vararg args: Any,
//        block: () -> A
//): A = this.info { "Starting operation [$operationName]." }
//        .let { this.debug { "Executing [$operationName] with args [$args]." } }
//        .let { block() }
//        .also { this.info("Successfully performed [$operationName].") }
//        .also { this.debug { "[$operationName] returns [$it]" } }