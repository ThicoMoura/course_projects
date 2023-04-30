package com.moura.thico.credit.application.system.repository

import com.moura.thico.credit.application.system.entity.Address
import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.entity.Customer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepository: CreditRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var creditOne: Credit
    private lateinit var creditTwo: Credit

    @BeforeEach fun setup() {
        customer = testEntityManager.persist(buildCustomer())
        creditOne = testEntityManager.persist(buildCredit(customer = customer))
        creditTwo = testEntityManager.persist(buildCredit(customer = customer))
    }

    @Test
    fun `should find credit by credit code`() {
        //given
        val creditCodeOne = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val creditCodeTwo = UUID.fromString("49f740be-46a7-449b-84e7-ff5b7986d7ef")
        creditOne.creditCode = creditCodeOne
        creditTwo.creditCode = creditCodeTwo
        //when
        val fakeCreditOne: Credit = creditRepository.findByCreditCode(creditCodeOne)!!
        val fakeCreditTwo: Credit = creditRepository.findByCreditCode(creditCodeTwo)!!
        //then
        Assertions.assertThat(fakeCreditOne).isNotNull
        Assertions.assertThat(fakeCreditTwo).isNotNull
        Assertions.assertThat(fakeCreditOne).isSameAs(creditOne)
        Assertions.assertThat(fakeCreditTwo).isSameAs(creditTwo)
    }

    @Test
    fun `should find all credits by customer id`() {
        //given
        val customerId: Long = 1L
        //when
        val creditList: List<Credit> = creditRepository.findAllByCustomerId(customerId)
        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(creditOne, creditTwo)

    }

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2),
        numberOfInstallment: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallment = numberOfInstallment,
        customer = customer
    )

    private fun buildCustomer(
        firstName: String = "Thiago",
        lastName: String = "Moura",
        cpf: String = "12345678909",
        email: String = "test@test",
        password: String = "123456",
        zipCode: String = "123456",
        street: String = "Rua da rua",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income = income
    )
}