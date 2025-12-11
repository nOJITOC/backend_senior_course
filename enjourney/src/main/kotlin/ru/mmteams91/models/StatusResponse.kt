package ru.mmteams91.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val status: String
)