package com.vinio.sportapplication.bottomNavigation.mainScreens

import android.util.Log
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

// Ваш новый HttpClient на основе CIO
private val client = HttpClient(CIO) {
    // Устанавливаем поддержку ContentNegotiation для работы с JSON
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true // Игнорировать неизвестные ключи
        })
    }

    // Устанавливаем логирование запросов и ответов
    install(Logging) {
        level = LogLevel.ALL // Логировать все запросы и ответы
    }
}

suspend fun fetchEvents(): List<EventEntity> {
    return withContext(Dispatchers.IO) {
        try {
            // Отправка запроса на сервер
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/event/user/deprecate/1")

            // Десериализация JSON-массива в List<Event>
            val events: List<EventEntity> = response.body()

            return@withContext events
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList<EventEntity>()
        }
    }
}

// Функция для получения одного события
suspend fun fetchOneEvent(): EventEntity {
    return withContext(Dispatchers.IO) {
        try {
            // Отправка запроса на сервер
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/event/dto/1")
            Log.d("[RESP]", response.toString())
            Log.d("[RESP]", response.body())
            // Десериализация JSON-объекта в Event
            val event = Json.decodeFromString(response.body()) as EventEntity // Используем body() для получения объекта

            return@withContext event
        } catch (e: Exception) {
            e.printStackTrace()
            // В случае ошибки возвращаем пустое событие с дефолтными значениями
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
}
