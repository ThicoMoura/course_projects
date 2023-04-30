package com.moura.thico.credit.application.system.service

import com.moura.thico.credit.application.system.entity.Address
import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.entity.Customer
import com.moura.thico.credit.application.system.exception.BusinessException
import com.moura.thico.credit.application.system.repository.CreditRepository
import com.moura.thico.credit.application.system.repository.CustomerRepository
import com.moura.thico.credit.application.system.service.impl.CreditService
import com.moura.thico.credit.application.system.service.impl.CustomerService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var customerService: CustomerService
    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @BeforeEach
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `should create credit`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { customerService.findById(fakeId) } returns fakeCustomer
        every { creditRepository.save(any()) } returns fakeCredit
        //when
        val actual: Credit = creditService.save(fakeCredit)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should find all credit by customer id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeListCredit: List<Credit> = mutableListOf(buildCredit(customer = fakeCustomer), buildCredit(customer = fakeCustomer))
        every { creditRepository.findAllByCustomerId(fakeId) } returns fakeListCredit
        //when
        val actual: List<Credit> = creditService.findAllByCustomer(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual.size).isEqualTo(2)
        Assertions.assertThat(actual).isExactlyInstanceOf(ArrayList::class.java)
        Assertions.assertThat(actual).isSameAs(fakeListCredit)
        verify(exactly = 1) { creditRepository.findAllByCustomerId(fakeId) }
    }

    @Test
    fun `should find credit by credit code`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(creditCode = fakeCreditCode, customer = fakeCustomer)
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit
        //when
        val actual: Credit = creditService.findByCreditCode(fakeId, fakeCreditCode)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Credit::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }

    @Test
    fun `should not find credit by invalid credit code and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCreditCode: UUID = UUID.randomUUID()
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns null
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { creditService.findByCreditCode(fakeId, fakeCreditCode) }
            .withMessage("Creditcode $fakeCreditCode not found")
        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }

    @Test
    fun `should not find credit by invalid customer id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(creditCode = fakeCreditCode, customer = fakeCustomer)
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { creditService.findByCreditCode(Random().nextLong(), fakeCreditCode) }
            .withMessage("Contact admin")
        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }

    private fun buildCredit(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2),
        numberOfInstallment: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditCode = creditCode,
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
        id: Long = 1L
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
        income = income,
        id = id
    )
}