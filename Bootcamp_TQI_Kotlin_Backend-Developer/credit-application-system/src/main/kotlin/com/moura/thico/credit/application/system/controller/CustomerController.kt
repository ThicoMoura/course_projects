package com.moura.thico.credit.application.system.controller

import com.moura.thico.credit.application.system.dto.request.CustomerDto
import com.moura.thico.credit.application.system.dto.request.CustomerUpdateDto
import com.moura.thico.credit.application.system.dto.response.CustomerView
import com.moura.thico.credit.application.system.entity.Customer
import com.moura.thico.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerView> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomerView(savedCustomer))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> =
        ResponseEntity.status(HttpStatus.OK).body(CustomerView(this.customerService.findById(id)))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val update: Customer = customerUpdateDto.toEntity(customer)
        val updated: Customer = this.customerService.save(update)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(updated))
    }
}