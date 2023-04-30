package com.moura.thico.credit.application.system.service.impl

import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.exception.BusinessException
import com.moura.thico.credit.application.system.repository.CreditRepository
import com.moura.thico.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        this.customerService.findById(1)
        return this.creditRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessException("Creditcode $creditCode not found"))
        return if (credit.customer?.id == customerId) credit else throw BusinessException("Contact admin")
    }
}