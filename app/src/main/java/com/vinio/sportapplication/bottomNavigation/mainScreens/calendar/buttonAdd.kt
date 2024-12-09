package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AddEventPopup(onDismiss: () -> Unit, viewModel: EventViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()

    // Состояния для полей ввода
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var status by remember { mutableStateOf(TextFieldValue("")) }
    var startTime by remember { mutableStateOf(TextFieldValue("")) }
    var endTime by remember { mutableStateOf(TextFieldValue("")) }

    Popup(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // Полупрозрачный фон
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
                    text = "Добавить событие",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Поля ввода для события
                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (title.text.isEmpty()) {
                            Text("Введите название", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (description.text.isEmpty()) {
                            Text("Введите описание", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                BasicTextField(
                    value = status,
                    onValueChange = { status = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (status.text.isEmpty()) {
                            Text("Введите статус", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                BasicTextField(
                    value = startTime,
                    onValueChange = { startTime = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (startTime.text.isEmpty()) {
                            Text("Введите время начала", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                BasicTextField(
                    value = endTime,
                    onValueChange = { endTime = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (endTime.text.isEmpty()) {
                            Text("Введите время окончания", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка "Добавить"
                Button(
                    onClick = {
                        // Отправка данных на сервер через Ktor
                        val newEvent = EventEntity(
                            id = 0, // Можно генерировать или оставить 0 для новой записи
                            startTime = LocalDateTime.parse(startTime.text, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                            endTime = LocalDateTime.parse(endTime.text, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                            status = status.text,
                            title = title.text,
                            description = description.text,
                            calories = 100, // Пример значения
                            category = "General", // Пример категории
                            createdAt = LocalDateTime.now(),
                            updatedAt = LocalDateTime.now()
                        )
                        coroutineScope.launch {
//                            viewModel.addEvent(newEvent) // Ваш метод для отправки запроса через Ktor
                        }
                        onDismiss() // Закрыть Popup
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Добавить")
                }

                // Кнопка закрытия Popup
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text(text = "Закрыть")
                }
            }
        }
    }
}
