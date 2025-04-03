package com.vinio.sportapplication.bottomNavigation.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Отправляем уведомление
        sendNotification(applicationContext, "event", "It's time for your event!")

        // Возвращаем результат
        return Result.success()
    }
}
