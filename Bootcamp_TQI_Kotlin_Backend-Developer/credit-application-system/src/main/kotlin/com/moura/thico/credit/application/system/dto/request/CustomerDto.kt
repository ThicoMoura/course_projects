package com.moura.thico.credit.application.system.dto.request

import com.moura.thico.credit.application.system.entity.Address
import com.moura.thico.credit.application.system.entity.Customer
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "First Name must not be empty") val firstName: String,
    @field:NotEmpty(message = "Last Name must not be empty") val lastName: String,
    @field:NotEmpty(message = "CPF must not be empty")
    @field:CPF(message = "CPF should be valid") val cpf: String,
    @field:NotNull(message = "Income must not be null") @field:Min(value = 1) val income: BigDecimal,
    @field:NotEmpty(message = "Email must not be empty") @field:Email(message = "Email should be valid") val email: String,
    @field:NotEmpty(message = "Password must not be empty") val password: String,
    @field:NotEmpty(message = "Zip Code must not be empty") val zipCode: String,
    @field:NotEmpty(message = "Street must not be empty") val street: String,
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}