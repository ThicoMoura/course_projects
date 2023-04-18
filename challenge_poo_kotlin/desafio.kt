import br.moura.thico.domain.Contents
import br.moura.thico.domain.Formation
import br.moura.thico.domain.Level
import br.moura.thico.domain.User

fun main() {
    // *Users*
    val thiago = User("Thiago")
    val matheus = User("Matheus")
    val daniel = User("Daniel")

    // *Contents*
    val introduction = Contents("Introduction", 20)
    val logicalReasoning = Contents("Logical Reasoning", 30)
    val essentialKotlin = Contents("Essential Kotlin", 50)

    // *Formations*
    val basic = Formation("Basic programming", Level.BASIC)
    val intermediate = Formation("Kotlin basic to intermediate", Level.INTERMEDIATE)
    val hard = Formation("Essential Kotlin - everything to become a kotlin programmer", Level.HARD)

    // *Basic*
    basic.addContent(introduction)
    basic.enroll(daniel)

    println("*---------------*")
    println(basic.getName() + ": " + basic.getLevel())
    println("* Subscribed users: ")
    basic.getSubscribed().stream().forEach{ println(" -" + it.getName()) }
    println("* Contents:")
    basic.getContents().stream().forEach { println(" -" + it.getName() + " - " + it.getDuration()) }

    // *Intermediate*
    intermediate.addContent(introduction)
    intermediate.addContent(logicalReasoning)

    intermediate.enroll(daniel)
    intermediate.enroll(matheus)

    println("*---------------*")
    println(intermediate.getName() + ": " + intermediate.getLevel())
    println("* Subscribed users: ")
    intermediate.getSubscribed().stream().forEach{ println(" -" + it.getName()) }
    println("* Contents:")
    intermediate.getContents().stream().forEach { println(" -" + it.getName() + " - " + it.getDuration()) }

    // *Hard*
    hard.addContent(introduction)
    hard.addContent(logicalReasoning)
    hard.addContent(essentialKotlin)

    hard.enroll(matheus)
    hard.enroll(thiago)

    println("*---------------*")
    println(hard.getName() + ": " + hard.getLevel())
    println("* Subscribed users: ")
    hard.getSubscribed().stream().forEach{ println(" -" + it.getName()) }
    println("* Contents:")
    hard.getContents().stream().forEach { println(" -" + it.getName() + " - " + it.getDuration()) }
    println("*---------------*")
}
