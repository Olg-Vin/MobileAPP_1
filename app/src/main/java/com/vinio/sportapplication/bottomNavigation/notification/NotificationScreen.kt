package com.vinio.sportapplication.bottomNavigation.notification

import android.content.Context
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
        Text("Notification Settings", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(24.dp))

        // Переключатель для уведомлений о событиях
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Event Notifications")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isEventNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    isEventNotificationsEnabled = isEnabled
                    // Включаем или отключаем уведомления для события
                    setNotificationsEnabled(context, "events_channel", isEnabled)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель для уведомлений о новостях
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("News Notifications")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isNewsNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    isNewsNotificationsEnabled = isEnabled
                    // Включаем или отключаем уведомления для новостей
                    setNotificationsEnabled(context, "news_channel", isEnabled)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель для уведомлений о итогах дня
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Daily Summary Notifications")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isDailySummaryNotificationsEnabled,
                onCheckedChange = { isEnabled ->
                    isDailySummaryNotificationsEnabled = isEnabled
                    // Включаем или отключаем уведомления для итогов дня
                    setNotificationsEnabled(context, "daily_summary_channel", isEnabled)
                }
            )
        }
    }
}
