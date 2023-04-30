package com.moura.thico.credit.application.system.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate

private const val MAX_MONTH = 3L
class MaxDateValidator : ConstraintValidator<MaxDate, LocalDate> {
    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean {
        return value!!.isBefore(LocalDate.now().plusMonths(MAX_MONTH))
    }
}
