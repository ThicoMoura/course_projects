package br.moura.thico.domain

class Formation(private val name: String, private val level: Level) {

    private val subscribed = mutableListOf<User>()
    private val contents = mutableListOf<Contents>()

    fun getName(): String {
        return name
    }

    fun getLevel(): Level {
        return level
    }

    fun enroll(user: User) {
        subscribed.add(user)
    }

    fun getSubscribed(): MutableList<User> {
        return subscribed
    }

    fun addContent(content: Contents) {
        contents.add(content)
    }

    fun getContents(): MutableList<Contents> {
        return contents
    }
}