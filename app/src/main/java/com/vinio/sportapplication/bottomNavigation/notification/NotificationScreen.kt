package com.vinio.sportapplication.bottomNavigation.notification

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.SettingsButton

@Composable
fun NotificationSettingsScreen(
    navController: NavController = rememberNavController()) {

    SettingsButton(
        text = "Назад",
        onClick = { navController.popBackStack() }
    )

    val context = LocalContext.current

    // Состояние для управления уведомлениями
    var isEventNotificationsEnabled by remember { mutableStateOf(true) }
    var isNewsNotificationsEnabled by remember { mutableStateOf(true) }
    var isDailySummaryNotificationsEnabled by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {


        Spacer(modifier = Modifier.height(104.dp))

        // Переключатель для уведомлений о событиях
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Уведомлять о событиях")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isEventNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    Log.d("enableNotification", isEnabled.toString())
                    isEventNotificationsEnabled = isEnabled
                    setNotificationsEnabled(context, "events_channel", isEnabled)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель для уведомлений о новостях
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Уведомлять о новостях")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isNewsNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    Log.d("enableNotification", isEnabled.toString())
                    isNewsNotificationsEnabled = isEnabled
                    // Включаем или отключаем уведомления для новостей
                    setNotificationsEnabled(context, "news_channel", isEnabled)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель для уведомлений о итогах дня
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Пуши с итогом калорий за день")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isDailySummaryNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    Log.d("enableNotification", isEnabled.toString())
                    isDailySummaryNotificationsEnabled = isEnabled
                    // Включаем или отключаем уведомления для итогов дня
                    setNotificationsEnabled(context, "daily_summary_channel", isEnabled)
                }
            )
        }
    }
}
