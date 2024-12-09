package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.CardMain
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.DateSelectorScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import java.time.LocalDate

@Preview
@Composable
fun CalendarScreen(viewModel: EventViewModel = viewModel()) {
    val events = viewModel.events.value
    LaunchedEffect(Unit) {
        viewModel.getEvents()
    }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showPopup by remember { mutableStateOf(false) }
//    val eventsByDate = events.groupBy { it.startTime }

    FloatingActionButton(
        onClick = { showPopup = true },
        modifier = Modifier
            .padding(16.dp),  // Отступы от краев экрана
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Добавить")
    }


    // Открытие Popup
    if (showPopup) {
        AddEventPopup(onDismiss = { showPopup = false }, viewModel = viewModel)
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
}

fun generateDatesList(startDate: LocalDate, daysCount: Int): List<LocalDate> {
    return List(daysCount) { startDate.plusDays(it.toLong()) }
}


