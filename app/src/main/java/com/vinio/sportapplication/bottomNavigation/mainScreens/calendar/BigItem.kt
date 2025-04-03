package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.formatTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SchedulePopup(
    date: LocalDate,
    events: List<EventEntity>,
    onDismiss: () -> Unit = {}
) {
    Popup(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "${date.dayOfWeek.name}, ${date.format(DateTimeFormatter.ofPattern("dd.MM.yy"))}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Если есть события, выводим их в LazyColumn
                if (events.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f) // Заставляем LazyColumn занимать всё доступное пространство
                    ) {
                        items(events) { event ->
                            TaskItem(
                                title = event.title,
                                time = formatTime(event.startTime),
                                status = event.status
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                } else {
                    Text("События не найдены", fontSize = 16.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(16.dp)) // Добавляем дополнительный отступ перед кнопкой
                Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Закрыть")
                }
            }
        }
    }
}


@Composable
fun TaskItem(title: String, time: String, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDFFFE0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = status, fontSize = 14.sp, color = Color.Gray)
        }
        Text(text = time, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

