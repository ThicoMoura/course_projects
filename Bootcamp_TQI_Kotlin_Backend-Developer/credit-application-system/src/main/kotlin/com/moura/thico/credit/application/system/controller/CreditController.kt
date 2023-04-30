package com.moura.thico.credit.application.system.controller

import com.moura.thico.credit.application.system.dto.request.CreditDto
import com.moura.thico.credit.application.system.dto.response.CreditView
import com.moura.thico.credit.application.system.dto.response.CreditViewList
import com.moura.thico.credit.application.system.entity.Credit
import com.moura.thico.credit.application.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(CreditView(credit))
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(this.creditService.findAllByCustomer(customerId).stream()
                .map { credit: Credit -> CreditViewList(credit) }
                .collect(Collectors.toList()))

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID) : ResponseEntity<CreditView> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(CreditView(this.creditService.findByCreditCode(customerId, creditCode)))
}
