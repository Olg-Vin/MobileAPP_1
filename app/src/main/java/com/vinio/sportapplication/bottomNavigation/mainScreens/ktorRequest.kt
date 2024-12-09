package com.vinio.sportapplication.bottomNavigation.mainScreens

import com.vinio.sportapplication.bottomNavigation.entity.Event
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
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

// Пример запроса с использованием этого клиента
suspend fun fetchEvents(): List<Event> {
    return withContext(Dispatchers.IO) {
        try {
            // Получаем ответ от сервера
            val response: HttpResponse = client.get("https://your-api-endpoint.com/events")

            // Парсим тело ответа в список событий
            response.body<List<Event>>()
        } catch (e: Exception) {
            // Обработка ошибок
            e.printStackTrace()
            emptyList()
        }
    }
}

