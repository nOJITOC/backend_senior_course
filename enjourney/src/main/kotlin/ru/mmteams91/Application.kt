package ru.mmteams91

import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.mmteams91.tasks.sendAnalyticsPeriodically
import ru.mmteams91.tasks.syncCachePeriodically

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    val appScope = CoroutineScope(SupervisorJob())
    syncCachePeriodically(appScope)
    sendAnalyticsPeriodically(appScope)
}
