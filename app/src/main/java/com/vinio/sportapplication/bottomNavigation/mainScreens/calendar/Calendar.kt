package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.DateSelectorScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import java.time.LocalDate

@Composable
fun CalendarScreen(viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current
    val events = viewModel.events.value
    LaunchedEffect(Unit) {
        viewModel.getEvents(context)
    }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showPopup by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Основной контент с колонкой и списком
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp) // Отступ снизу, чтобы не перекрывать кнопку
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            DateSelectorScreen(
                selectedDate = selectedDate,
                onDateChanged = { newDate -> selectedDate = newDate }
            )
            Spacer(modifier = Modifier.height(10.dp))

            val eventsByDate: Map<LocalDate, List<EventEntity>> = events.groupBy { it.startTime.toLocalDate() }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                val dates = generateDatesList(LocalDate.now(), daysCount = 30)
                items(dates) { date ->
                    val eventsForDate = eventsByDate[date].orEmpty()
                    CalendarItem(
                        number = date,
                        dayOfWeek = date.dayOfWeek.name,
                        eventCount = eventsForDate.size,
                        events = eventsForDate
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // Кнопка поверх списка, с zIndex для правильного отображения
        FloatingActionButton(
            onClick = { showPopup = true },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Размещение в правом нижнем углу
                .padding(0.dp, 0.dp, 16.dp, 100.dp) // Отступы от краев экрана
                .zIndex(1f) // Устанавливаем более высокий zIndex для кнопки
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Добавить")
        }

        // Открытие попапа при клике на кнопку
        if (showPopup) {
            AddEventPopup(onDismiss = { showPopup = false }, viewModel = viewModel)
        }
    }
}

fun generateDatesList(startDate: LocalDate, daysCount: Int): List<LocalDate> {
    return List(daysCount) { startDate.plusDays(it.toLong()) }
}