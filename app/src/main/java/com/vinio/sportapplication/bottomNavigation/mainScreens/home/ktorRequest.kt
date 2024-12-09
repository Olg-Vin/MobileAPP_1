package com.vinio.sportapplication.bottomNavigation.mainScreens.home

import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

suspend fun fetchEvents(): List<EventEntity> {
    return withContext(Dispatchers.IO) {
        try {
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/event/user/1")
            val events: List<EventEntity> = response.body()

            return@withContext events
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList<EventEntity>()
        }
    }
}

/*suspend fun fetchOneEvent(): EventEntity {
    return withContext(Dispatchers.IO) {
        try {
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/event/dto/1")
            Log.d("[RESP]", response.toString())
            Log.d("[RESP]", response.body())
            val event = Json.decodeFromString(response.body()) as EventEntity

            return@withContext event
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext EventEntity(
                id = -1,
                startTime = "LocalDateTime.now()",
                endTime = "LocalDateTime.now()",
                status = "Unknown",
                title = "Error",
                description = "No description available",
                calories = 0,
                category = "Unknown",
                createdAt = "LocalDateTime.now()",
                updatedAt = "LocalDateTime.now()"
            )
        }
    }
}*/
