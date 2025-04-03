package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import android.content.Context
import android.util.Log
import com.vinio.sportapplication.bottomNavigation.entity.EmailUpdateRequest
import com.vinio.sportapplication.bottomNavigation.entity.JwtResponse
import com.vinio.sportapplication.bottomNavigation.entity.PasswordUpdateRequest
import com.vinio.sportapplication.bottomNavigation.entity.UserData
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.clearToken
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.client
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.getToken
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.saveToken
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchUserData(context: Context): UserData? {
    return withContext(Dispatchers.IO) {
        try {
            val token: String = getToken(context).toString()
            val response: HttpResponse = client.get("http://10.0.2.2:8080/api/user/data") {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        "Bearer $token")
                }
            }

            val userData: UserData = response.body()
            Log.d("fetchUserData", "User data received: $userData")
            return@withContext userData
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }
    }
}

suspend fun changeUserPassword(context: Context, oldPassword: String, newPassword: String): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val token: String = getToken(context).toString()
            val requestBody = PasswordUpdateRequest(oldPassword, newPassword)

            val response: HttpResponse = client.put("http://10.0.2.2:8080/api/user/updatePassword") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                setBody(requestBody)
            }

            if (response.status == HttpStatusCode.OK) {
                Log.d("changeUserPassword", "Password updated successfully")
                return@withContext true
            } else {
                Log.e("changeUserPassword", "Failed to update password: ${response.status}")
                return@withContext false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}

suspend fun changeUserEmail(context: Context, oldEmail: String, newEmail: String): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val token: String = getToken(context).toString()
            val requestBody = EmailUpdateRequest(oldEmail, newEmail)

            val response: HttpResponse = client.put("http://10.0.2.2:8080/api/user/updateEmail") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                setBody(requestBody)
            }

            if (response.status == HttpStatusCode.OK) {
                val jwtResponse: JwtResponse = response.body()

                // Сохраняем новый access и refresh токены
                saveToken(context, jwtResponse.accessToken)
//                saveRefreshToken(context, jwtResponse.refreshToken)

                Log.d("changeUserEmail", "Email обновлён, новые токены сохранены")
                return@withContext true
            } else {
                Log.e("changeUserEmail", "Ошибка обновления email: ${response.status}")
                return@withContext false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}

suspend fun deleteAccount(context: Context): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val token: String = getToken(context).toString()

            val response: HttpResponse = client.delete("http://10.0.2.2:8080/api/user/deleteAccount") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }

            if (response.status == HttpStatusCode.OK) {
                clearToken(context) // Удаляем токен из SharedPreferences
                Log.d("deleteAccount", "Аккаунт удалён")
                return@withContext true
            } else {
                Log.e("deleteAccount", "Ошибка удаления аккаунта: ${response.status}")
                return@withContext false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}

