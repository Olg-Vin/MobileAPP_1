package com.vinio.sportapplication.bottomNavigation.mainScreens.home

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Объявляем глобальный клиент
val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

// Запрос на получение списка событий
suspend fun fetchEvents(context: Context): List<EventEntity> {
    return withContext(Dispatchers.IO) {
        try {
            val token: String = getToken(context).toString()
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/events/my") {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        "Bearer $token"
                    )
                }
            }
            val events: List<EventEntity> = response.body()
            Log.d("response", response.body())

            return@withContext events
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList<EventEntity>()
        }
    }
}

// Функция для отправки события на сервер
suspend fun sendEventToServer(event: EventEntity, context: Context): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            // Получаем токен из SharedPreferences
            val token: String = getToken(context).toString()

            // Отправляем запрос на сервер с данными события
            val response: HttpResponse = client.post("http://10.0.2.2:8080/api/events") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token") // Добавляем токен в заголовок
                }
                contentType(ContentType.Application.Json)
                setBody(event) // Отправляем данные события в теле запроса
            }

            // Проверка статуса ответа
            if (response.status == HttpStatusCode.Created) {
                Log.d("sendEvent", "Event successfully sent to server")
                return@withContext true
            } else {
                Log.e("sendEvent", "Failed to send event: ${response.status}")
                return@withContext false
            }
        } catch (e: Exception) {
            Log.e("sendEvent", "Error sending event: ${e.message}")
            return@withContext false
        }
    }
}

suspend fun deleteEventFromServer(eventId: Long, context: Context): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            // Получаем токен из SharedPreferences
            val token: String = getToken(context).toString()

            // Отправляем запрос на сервер для удаления события
            val response: HttpResponse = client.delete("http://10.0.2.2:8080/api/events/$eventId") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token") // Добавляем токен в заголовок
                }
            }

            // Проверка статуса ответа
            if (response.status == HttpStatusCode.NoContent) {
                Log.d("deleteEvent", "Event successfully deleted from server")
                return@withContext true
            } else {
                Log.e("deleteEvent", "Failed to delete event: ${response.status}")
                return@withContext false
            }
        } catch (e: Exception) {
            Log.e("deleteEvent", "Error deleting event: ${e.message}")
            return@withContext false
        }
    }
}



// Данные для запроса на регистрацию
@Serializable
data class JwtRequest(
    val username: String,
    val password: String
)

@Serializable
data class JwtResponse(
    val type: String,
    val accessToken: String,
    val refreshToken: String
)

// Функция для регистрации пользователя
suspend fun registerUser(context: Context, username: String, password: String): JwtResponse {
    return withContext(Dispatchers.IO) {
        try {
            val registerRequest = JwtRequest(username, password)

            val response: HttpResponse = client.post("http://10.0.2.2:8080/api/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(registerRequest)
            }
            val token: String = response.body<JwtResponse>().accessToken
            saveToken(context, token)

            return@withContext response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext JwtResponse(
                "Bearer",
                "",
                ""
            )
        }
    }
}

suspend fun loginUser(context: Context, username: String, password: String): JwtResponse {
    return withContext(Dispatchers.IO) {
        try {
            val loginRequest = JwtRequest(username, password)

            val response: HttpResponse = client.post("http://10.0.2.2:8080/api/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }
            val token: String = response.body<JwtResponse>().accessToken
            saveToken(context, token)

            return@withContext response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext JwtResponse(
                "Bearer",
                "",
                ""
            )
        }
    }
}


fun saveToken(context: Context, token: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("ACCESS_TOKEN", token)
    editor.apply()
}

fun getToken(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    return sharedPreferences.getString("ACCESS_TOKEN", null)
}

fun clearToken(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.remove("ACCESS_TOKEN")
    editor.apply()
}
