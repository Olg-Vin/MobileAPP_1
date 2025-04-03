package com.vinio.sportapplication.bottomNavigation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.vinio.sportapplication.R

/*fun sendNotification(context: Context, message: String) {
    val notificationId = 1
    val channelId = "event_notifications_channel"

    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Event Reminder")
        .setContentText(message)
        .setSmallIcon(R.drawable.icon)
        .setAutoCancel(true)
        .build()

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(notificationId, notification)
}*/

fun sendNotification(context: Context, category: String, message: String) {
    val channelId = when (category) {
        "event" -> "events_channel"
        "news" -> "news_channel"
        "daily_summary" -> "daily_summary_channel"
        else -> "events_channel" // по умолчанию канал для событий
    }

    val notificationId = 1
    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Notification Title")
        .setContentText(message)
        .setSmallIcon(R.drawable.icon)
        .setAutoCancel(true)
        .build()

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(notificationId, notification)
}

fun areNotificationsEnabledForChannel(context: Context, channelId: String): Boolean {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val channel = notificationManager.getNotificationChannel(channelId)
    return channel.importance != NotificationManager.IMPORTANCE_NONE
}


fun createNotificationChannels(context: Context) {
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Канал для событий
        val eventChannel = NotificationChannel(
            "events_channel", // ID канала
            "Event Notifications",
            NotificationManager.IMPORTANCE_HIGH // Высокая важность
        ).apply {
            description = "Notifications for events"
        }

        // Канал для новостей
        val newsChannel = NotificationChannel(
            "news_channel", // ID канала
            "News Notifications",
            NotificationManager.IMPORTANCE_DEFAULT // Средняя важность
        ).apply {
            description = "Notifications for news"
        }

        // Канал для итогов дня
        val dailySummaryChannel = NotificationChannel(
            "daily_summary_channel", // ID канала
            "Daily Summary Notifications",
            NotificationManager.IMPORTANCE_LOW // Низкая важность
        ).apply {
            description = "Notifications for daily summary"
        }

        // Регистрируем каналы в системе
        notificationManager.createNotificationChannel(eventChannel)
        notificationManager.createNotificationChannel(newsChannel)
        notificationManager.createNotificationChannel(dailySummaryChannel)
    }
}

fun setNotificationsEnabled(context: Context, channelId: String, enabled: Boolean) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Получаем канал уведомлений
    val channel = notificationManager.getNotificationChannel(channelId)

    // Изменяем важность канала
    if (channel != null) {
        if (enabled) {
            // Если включаем, устанавливаем нужную важность
            channel.importance = NotificationManager.IMPORTANCE_DEFAULT
        } else {
            // Если отключаем, устанавливаем важность в NONE
            channel.importance = NotificationManager.IMPORTANCE_NONE
        }

        // Применяем изменения
        notificationManager.createNotificationChannel(channel)
    }
}


/*
val category = "news" // например, новости
val message = "Here is the latest news!"

// Проверяем, включены ли уведомления для канала новостей
if (areNotificationsEnabledForChannel(context, "news_channel")) {
    sendNotification(context, category, message)
} else {
    // Возможно, показываем другое сообщение или не отправляем уведомление
    Log.d("Notification", "News notifications are disabled.")
}
*/
