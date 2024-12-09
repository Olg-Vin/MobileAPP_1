package com.vinio.sportapplication.bottomNavigation.mainScreens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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

    getOneEvent()
//    getEvents()
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
            Log.d("[Event]", "Event Title: ${it.title}, Start Time: ${it.startTime}")
        }
    }
}

fun getOneEvent() {
    CoroutineScope(Dispatchers.Main).launch {
        val event = fetchOneEvent() // Получаем список событий
        println("Event Title: ${event.title}, Start Time: ${event.startTime}")
        Log.d("[Event]", "Event Title: ${event.title}, Start Time: ${event.startTime}")

    }
}

