package com.moura.thico.credit.application.system.dto.response

import com.moura.thico.credit.application.system.entity.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
) {

    constructor(credit: Credit) : this (
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallment,
    )
}
