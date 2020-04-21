package org.blackhouse.participant.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "Participant")
data class Participant(@Id val id: String?,
                       val firstName: String = "",
                       val lastName: String = "",
                       val age: Int = 18,
                       val email: String = "",
                       val phone: String
) {
    constructor(firstName: String = "",
                lastName: String = "",
                age: Int = 18,
                email: String = "",
                phone: String
    ) : this(null, firstName, lastName, age, email, phone)
}
