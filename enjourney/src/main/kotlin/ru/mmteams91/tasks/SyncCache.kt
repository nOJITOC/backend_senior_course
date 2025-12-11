package ru.mmteams91.tasks

import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

fun Application.syncCachePeriodically(scope: CoroutineScope) {
    scope.launch {
        while (isActive) {
            try {
                val isFailing = Random.nextInt(0, 100) < 30
                delay(10.seconds)
                if (isFailing) {
                    throw IOException("Failed to update cache!")
                }
                log.debug("Cache updated successfully!")
            } catch (e: IOException) {
                log.error("syncCachePeriodically", e)
            }
        }
    }
}