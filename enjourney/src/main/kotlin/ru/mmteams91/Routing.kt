package ru.mmteams91

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.delay
import ru.mmteams91.models.StatusResponse
import kotlin.time.Duration.Companion.seconds

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(StatusResponse("ok"))
        }
        post("/api/v1/quest/new") {
            call.respond(StatusResponse("ok"))
        }
        get("/hello/{name}") {
            val name = call.parameters["name"]
            call.respondText("Привет, ${name ?: "незнакомец"}!")
        }
        get("/api/v1/long-operation") {
            try {

                val shouldFall = call.queryParameters["shouldFail"]
                    ?.toBooleanStrictOrNull()
                    ?: false
                if (shouldFall) {
                    throw IllegalStateException("This operation failed intentionally.")
                }
                delay(5.seconds)
                call.respond(StatusResponse("Completed after 5 seconds"))
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.InternalServerError, StatusResponse(e.message.orEmpty()))
            }
        }
    }
}
