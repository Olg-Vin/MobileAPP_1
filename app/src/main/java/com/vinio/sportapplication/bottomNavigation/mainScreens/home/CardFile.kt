package com.vinio.sportapplication.bottomNavigation.mainScreens.home

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CardMain(event: EventEntity) {
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
                text = formatTime(event.startTime),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black // Черный цвет для времени
                )
            )
        }

        // Вызов диалога, если showDialog = true
        if (showDialog) {
            TaskPopupDialog(event = event, onDismiss = { showDialog = false })
        }
    }
}

// Пример форматирования времени
fun formatTime(time: LocalDateTime): String {
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}


