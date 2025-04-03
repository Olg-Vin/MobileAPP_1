package com.vinio.sportapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.notification.createNotificationChannels
import com.vinio.sportapplication.bottomNavigation.startScreens.StartNavGraph
import com.vinio.sportapplication.ui.theme.SportApplicationTheme

/**
 * Точка входа в приложение
 * начинает со стартового графа - регистрация, авторизация.
 * */
// TODO поработать над кешированием авторизации
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SportApplicationTheme {
                StartNavGraph(navController)
            }
        }

        /*val channelId = "event_notifications_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Event Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for event notifications"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }*/
        createNotificationChannels(this)

    }
}



