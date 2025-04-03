package com.vinio.sportapplication.bottomNavigation.mainScreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import kotlinx.coroutines.launch

/*@Composable
fun TaskScreen() {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showDialog = true }) {
            Text("Открыть карточку задачи")
        }

        if (showDialog) {
            TaskPopupDialog(onDismiss = { showDialog = false })
        }
    }
}*/
@Composable
fun TaskPopupDialog(event: EventEntity, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFEFF7E6),
            modifier = Modifier
                .fillMaxWidth(1f) // 90% ширины экрана
                .fillMaxHeight(0.6f) // 80% высоты экрана
        )  {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Заголовок
                Text(
                    text = event.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Описание
                Text(
                    text = event.description,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Список задач
                Text(
                    text = """
                        Категория: ${event.category}
                        Калории: ${event.calories}
                    """.trimIndent(),
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Время и статус
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Запланированное время:",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "${formatTime(event.startTime)} - ${formatTime(event.endTime)}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column {
                        Text(
                            text = "Статус:",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = event.status,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.6f))

                if (event.status == "In process") {
                    Button(
                        onClick = {
                            onDismiss()
                            event.status = "ready"
                            coroutineScope.launch {
                                sendEventToServer(
                                    event,
                                    context
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Отметить выполненым")
                    }
                }
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Закрыть")
                }
                Button(
                    onClick = { onDismiss()
                        coroutineScope.launch {
                            deleteEventFromServer(
                                event.id,
                                context
                            )
                        }},
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Удалить")
                }
            }
        }
    }
}
