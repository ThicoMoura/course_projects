package com.moura.thico.credit.application.system.exception

import java.time.LocalDateTime

data class ExceptionDetails(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val details: MutableMap<String, String?>
)
