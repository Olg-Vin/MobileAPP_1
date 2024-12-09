package com.vinio.sportapplication.bottomNavigation.mainScreens

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vinio.sportapplication.bottomNavigation.entity.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen1",
        textAlign = TextAlign.Center,
    )
}

@Composable
fun EventListScreen() {

//    val events = remember { mutableStateOf<List<Event>>(emptyList()) }

    getEvents()
/*
    // Используем LazyColumn для отображения списка
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp) // Оставляем место для Footer
    ) {
        items(events.value) { event ->
            CardMain(event) // Передаем объект event в CardMain для отображения
            Spacer(modifier = Modifier.height(8.dp))
        }
    }*/
}


fun getEvents() {
    CoroutineScope(Dispatchers.Main).launch {
        val events = fetchEvents() // Получаем список событий
        events.forEach {
            println("Event Title: ${it.title}, Start Time: ${it.startTime}")
        }
    }
}

