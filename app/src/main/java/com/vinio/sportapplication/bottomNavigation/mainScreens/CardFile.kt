package com.vinio.sportapplication.bottomNavigation.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.bottomNavigation.entity.Event
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//@Preview
@Composable
fun CardMain(event: Event) {
//fun CardMain() {
//    TODO потом удалить, это mock
    /*val event = Event(
        id = 1,
        userId = 123,
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now().plusHours(2),
        status = "выполняется",
        title = "Пробежка в лесу",
        description = "Выполнение упражнения для здоровья",
        calories = 150,
        category = "спорт",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )*/

    var showDialog by remember { mutableStateOf(false) }

    Card(
        onClick = { showDialog = true }, // Показываем диалог при нажатии
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp) // Закругленные углы
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Внутренний отступ
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = event.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black // Черный цвет для названия
                    )
                )
                Text(
                    text = "Статус - ${event.status}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray // Серая для статуса
                    )
                )
            }
            Text(
//                text = formatTime(event.startTime),
                text = event.startTime,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black // Черный цвет для времени
                )
            )
        }

        // Вызов диалога, если showDialog = true
        if (showDialog) {
            TaskPopupDialog(onDismiss = { showDialog = false }) // Закрытие диалога
        }
    }
}

// Пример форматирования времени
fun formatTime(time: LocalDateTime): String {
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}


