package com.vinio.sportapplication.bottomNavigation.mainScreens.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventViewModel() : ViewModel() {
    private val _events = mutableStateOf<List<EventEntity>>(emptyList())
    val events: State<List<EventEntity>> get() = _events
    fun getEvents(context: Context) {
        viewModelScope.launch {
            val events = fetchEvents(context)
            _events.value = events
        }
    }
    // Добавляем событие локально и отправляем на сервер
    fun addEvent(event: EventEntity, context: Context) {
        // Добавляем событие в локальный список
        _events.value += event

        // Отправляем событие на сервер
        viewModelScope.launch {
            val success = sendEventToServer(event, context)
            if (success) {
                Log.d("addEvent", "Event successfully added to server")
            } else {
                Log.e("addEvent", "Failed to add event to server")
            }
        }
    }
}

@Composable
fun EventListScreen(viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current
    val events = viewModel.events.value
    LaunchedEffect(Unit) {
        viewModel.getEvents(context)
    }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        DateSelectorScreen(
            selectedDate = selectedDate,
            onDateChanged = { newDate -> selectedDate = newDate }
        )

        Spacer(modifier = Modifier.height(10.dp))
        val filteredEvents = events.filter { event ->
            event.startTime.toLocalDate() == selectedDate
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 56.dp)
        ) {
            items(filteredEvents) { event ->
                CardMain(event)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

