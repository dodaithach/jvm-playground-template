import kotlinx.coroutines.*
import java.lang.RuntimeException

private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
    println(ex.toString())
}

private val normalScopeWithoutHandler = CoroutineScope(Job())
private val normalScopeWithHandler = CoroutineScope(Job() + exceptionHandler)
private val supervisedScopeWithoutHandler = CoroutineScope(SupervisorJob())
private val supervisedScopeWithHandler = CoroutineScope(SupervisorJob() + exceptionHandler)

fun main(args: Array<String>) = runBlocking {
    println("starting main()")

    normalScopeWithoutHandler.launch {
        actBadly()
    }
    normalScopeWithoutHandler.launch {
        actWell()
    }

    delay(2000)
    println("ending main()")
    return@runBlocking
}

fun actBadly() {
    println("acting badly...")
    throw RuntimeException("test coroutine exception catching")
}

fun actWell() {
    println("acting well...")
}