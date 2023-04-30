package com.moura.thico.credit.application.system.dto.request

import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.entity.Customer
import com.moura.thico.credit.application.system.validator.MaxDate
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Credit Value must not be null") val creditValue: BigDecimal,
    @field:Future(message = "First Installment day should be in the future")
    @field:MaxDate(message = "The day of the first installment must be a maximum of 3 months in the future") val dayFirstOfInstallment: LocalDate,
    @field:Min(value = 1, message = "The number of installments must be at least 1")
    @field:Max(value = 48, message = "The number of installments must be a maximum of 48") val numberOfInstallment: Int,
    @field:NotNull(message = "Customer ID must not be null") val customerId: Long,
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
