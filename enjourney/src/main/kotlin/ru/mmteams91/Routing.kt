package ru.mmteams91

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import ru.mmteams91.models.StatusResponse

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
    }
}
