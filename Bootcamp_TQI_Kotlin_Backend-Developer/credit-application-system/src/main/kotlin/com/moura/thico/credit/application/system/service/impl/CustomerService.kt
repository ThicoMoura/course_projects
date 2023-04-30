package com.moura.thico.credit.application.system.service.impl

import com.moura.thico.credit.application.system.entity.Customer
import com.moura.thico.credit.application.system.exception.BusinessException
import com.moura.thico.credit.application.system.repository.CustomerRepository
import com.moura.thico.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
        throw BusinessException("Id $id not found")
    }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}