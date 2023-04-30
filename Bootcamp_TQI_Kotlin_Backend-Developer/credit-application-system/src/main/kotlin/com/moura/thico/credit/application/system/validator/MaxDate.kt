package com.moura.thico.credit.application.system.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxDateValidator::class])
@MustBeDocumented
annotation class MaxDate (
    val message: String = "Please, enter a date within three months",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)