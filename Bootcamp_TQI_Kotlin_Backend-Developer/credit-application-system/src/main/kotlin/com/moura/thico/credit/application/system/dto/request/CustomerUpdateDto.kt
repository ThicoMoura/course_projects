package com.moura.thico.credit.application.system.dto.request

import com.moura.thico.credit.application.system.entity.Customer
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "First Name must not be empty") val firstName: String,
    @field:NotEmpty(message = "Last Name must not be empty") val lastName: String,
    @field:NotNull(message = "Income must not be null") @field:Min(value = 1) val income: BigDecimal,
    @field:NotEmpty(message = "Zip Code must not be empty") val zipCode: String,
    @field:NotEmpty(message = "Street must not be empty") val street: String,
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
