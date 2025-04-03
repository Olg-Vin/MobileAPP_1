package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import java.time.LocalDateTime

@Composable
fun AddEventPopup(onDismiss: () -> Unit, viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current

    val title = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    val status = remember { mutableStateOf(TextFieldValue("")) }
    val startTime = remember { mutableStateOf(TextFieldValue("")) }
    val endTime = remember { mutableStateOf(TextFieldValue("")) }
    val calories = remember { mutableStateOf(TextFieldValue("")) }
    val category = remember { mutableStateOf(TextFieldValue("")) }

    val focusManager = LocalFocusManager.current
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    Dialog(onDismissRequest = onDismiss) {
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

            // Field input
            @Composable
            fun inputField(
                value: MutableState<TextFieldValue>,
                placeholder: String,
                focusRequester: FocusRequester
            ) {
                BasicTextField(
                    value = value.value,
                    onValueChange = { value.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                focusManager.moveFocus(FocusDirection.Down) // Move focus to next field when user interacts
                            }
                        },
                    decorationBox = { innerTextField ->
                        if (value.value.text.isEmpty()) {
                            Text(placeholder, color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
            }

            inputField(title, "Введите название", titleFocusRequester)
            inputField(description, "Введите описание", descriptionFocusRequester)
            inputField(status, "Введите статус", titleFocusRequester)
            inputField(category, "Введите категорию", titleFocusRequester)
            inputField(startTime, "Введите время начала", descriptionFocusRequester)
            inputField(endTime, "Введите время окончания", titleFocusRequester)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        // Преобразуем строковые значения в нужные типы данных
                        val event = EventEntity(
                            id = 0L, // Пока ID не определено, можно установить 0 или генерировать ID на сервере
                            title = title.value.text,
                            description = description.value.text,
                            status = status.value.text,
//                            startTime = LocalDateTime.parse(startTime.value.text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//                            endTime = LocalDateTime.parse(endTime.value.text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            startTime = LocalDateTime.now(),
                            endTime = LocalDateTime.now(),
                            calories = calories.value.text.toIntOrNull() ?: 0,
                            category = category.value.text,
                            createdAt = LocalDateTime.now(),
                            updatedAt = LocalDateTime.now()
                        )

                        // Добавляем событие в ViewModel
                        viewModel.addEvent(event, context)

                        // Закрываем попап
                        onDismiss()
                    } catch (e: Exception) {
                        // Обработка ошибок парсинга времени или данных
                        // Например, можно показать сообщение об ошибке пользователю
                        println("Ошибка добавления события: ${e.message}")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Добавить")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Закрыть")
            }
        }
    }
}


