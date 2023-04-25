package br.moura.thico.domain

class Contents(private val name: String, private val duration: Int = 60) {
    fun getName(): String {
        return name
    }

    fun getDuration(): Int {
        return duration
    }
}