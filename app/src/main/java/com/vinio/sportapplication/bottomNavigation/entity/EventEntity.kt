package com.vinio.sportapplication.bottomNavigation.entity

import kotlinx.serialization.Serializable


@Serializable
data class EventEntity(
    val id: Long,
    val startTime: String,
    val endTime: String,
    val status: String,
    val title: String,
    val description: String,
    val calories: Int,
    val category: String,
    val createdAt: String,
    val updatedAt: String
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

