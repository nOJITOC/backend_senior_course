package ru.mmteams91.tasks

import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import java.net.ConnectException
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

fun Application.sendAnalyticsPeriodically(scope: CoroutineScope) {
    scope.launch {
        while (isActive) {
            try {
                val isFailing = Random.nextInt(0, 100) < 20
                delay(15.seconds)
                if (isFailing) {
                    throw ConnectException("Failed to send statistics!")
                }
                log.debug("Statistics sent successfully!")
            } catch (e: IOException) {
                log.error("sendAnalyticsPeriodically", e)
            }
        }
    }
}