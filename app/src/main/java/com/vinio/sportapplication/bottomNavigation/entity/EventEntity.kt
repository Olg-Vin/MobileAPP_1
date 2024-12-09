package com.vinio.sportapplication.bottomNavigation.entity

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class EventEntity(
    val id: Long,
    @Serializable(LocalDateTimeSerializer::class)
    val startTime: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val endTime: LocalDateTime,
    val status: String,
    val title: String,
    val description: String,
    val calories: Int,
    val category: String,
    @Serializable(LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)


@Serializable
data class EventResponse(
    val events: List<EventEntity>
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

