package com.vinio.sportapplication.bottomNavigation.mainScreens.Calculate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.DateSelectorScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import java.time.LocalDate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.vinio.sportapplication.bottomNavigation.notification.MyWorker
import java.time.ZoneId
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun CalculateScreen(viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current
    val events = viewModel.events.value
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(Unit) {
        viewModel.getEvents(context)
    }

    val filteredEvents = events.filter { event ->
        event.startTime.toLocalDate() == selectedDate
    }

    val totalCalories = filteredEvents.sumOf { it.calories }

    // Определим время конца дня (23:59:59)
    val endOfDay = selectedDate.atTime(23, 59, 59)

    // Текущее время в миллисекундах
    val currentTimeMillis = System.currentTimeMillis()

    // Время окончания дня в миллисекундах
    val endOfDayMillis = endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    // Рассчитываем задержку (сколько времени осталось до конца дня)
    val timeToTriggerNotification = endOfDayMillis - currentTimeMillis

    // Если время до конца дня больше 0, создаём задачу с отложенным уведомлением
    if (timeToTriggerNotification > 0) {
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(timeToTriggerNotification, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("total_calories" to totalCalories))
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        DateSelectorScreen(
            selectedDate = selectedDate,
            onDateChanged = { newDate -> selectedDate = newDate }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Отображение общего количества калорий
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "$totalCalories ",
                    modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "калорий за сегодня",
                    modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 56.dp)
        ) {
            items(filteredEvents) { event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = event.title,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = "${event.calories} ккал",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}
