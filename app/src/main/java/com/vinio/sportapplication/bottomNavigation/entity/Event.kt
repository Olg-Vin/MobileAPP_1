package com.vinio.sportapplication.bottomNavigation.entity

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class Event(
    val id: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    val startTime: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val endTime: LocalDateTime,
    val status: String,
    val title: String,
    val description: String,
    val calories: Int,
    val category: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)


@Serializable
data class EventResponse(
    val events: List<Event>
)


@Serializable
data class User(
    val id: Long,
    val name: String?,
    val email: String,
    val password: String?,
    val roles: List<Role>,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class Role(val role: String)

