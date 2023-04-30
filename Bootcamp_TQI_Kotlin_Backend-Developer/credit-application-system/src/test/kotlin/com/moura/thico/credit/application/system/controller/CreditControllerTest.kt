package com.moura.thico.credit.application.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.moura.thico.credit.application.system.dto.request.CreditDto
import com.moura.thico.credit.application.system.dto.request.CustomerDto
import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.entity.Customer
import com.moura.thico.credit.application.system.repository.CreditRepository
import com.moura.thico.credit.application.system.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CreditControllerTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var creditRepository: CreditRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/credits"
    }

    @BeforeEach
    fun setup() = creditRepository.deleteAll()

    @AfterEach
    fun tearDown() = creditRepository.deleteAll()

    @Test
    fun `should create a credit and return 201 status`() {
        //given
        val customer: Customer = customerRepository.save(builderCustomerDto().toEntity())
        val creditDto: CreditDto = builderCreditDto(customerId = customer.id!!)
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.emailCustomer").value("test@test"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.incomeCustomer").value("1000.0"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a credit with more 3 months to future and return 400 status`() {
        //given
        val creditDto: CreditDto = builderCreditDto(dayFirstOfInstallment = LocalDate.now().plusMonths(4))
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .content(valueAsString)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find credit by customer id and return 200 status`() {
        //given
        val customer: Customer = customerRepository.save(builderCustomerDto().toEntity())
        val creditOne: Credit = creditRepository.save(builderCreditDto(customerId = customer.id!!).toEntity())
        val creditTwo: Credit = creditRepository.save(builderCreditDto(customerId = customer.id!!).toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL?customerId=${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].creditValue").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].numberOfInstallment").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].creditValue").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].numberOfInstallment").value(3))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find credit with invalid customer id and return 400 status`() {
        //given
        val invalidId: Long = 2L
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL?customerId=$invalidId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find credit by credit code and customer id and return 200 status`() {
        //given
        val customer: Customer = customerRepository.save(builderCustomerDto().toEntity())
        val credit: Credit = creditRepository.save(builderCreditDto(customerId = customer.id!!).toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${credit.creditCode}?customerId=${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.emailCustomer").value("test@test"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.incomeCustomer").value("1000.0"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find credit by invalid credit code and customer id and return 400 status`() {
        //given
        val invalidCode: UUID = UUID.randomUUID()
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${invalidCode}?customerId=${Random().nextLong()}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find credit by credit code and invalid customer by id and return 400 status`() {
        //given
        val customer: Customer = customerRepository.save(builderCustomerDto().toEntity())
        val credit: Credit = creditRepository.save(builderCreditDto(customerId = customer.id!!).toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${credit.creditCode}?customerId=${Random().nextLong()}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    private fun builderCreditDto(
        creditValue: BigDecimal = BigDecimal.valueOf(1000.0),
        dayFirstOfInstallment: LocalDate = LocalDate.now().plusMonths(2),
        numberOfInstallment: Int = 3,
        customerId: Long = 1,
    ) = CreditDto(
        creditValue = creditValue,
        dayFirstOfInstallment = dayFirstOfInstallment,
        numberOfInstallment = numberOfInstallment,
        customerId = customerId,
    )

    private fun builderCustomerDto(
        firstName: String = "Thiago",
        lastName: String = "Moura",
        cpf: String = "12345678909",
        email: String = "test@test",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        password: String = "1234",
        zipCode: String = "000000",
        street: String = "Rua test, 123",
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        zipCode = zipCode,
        street = street
    )
}