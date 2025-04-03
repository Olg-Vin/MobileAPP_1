package com.vinio.sportapplication.bottomNavigation.mainScreens.calendar

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vinio.sportapplication.bottomNavigation.entity.EventEntity
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventViewModel
import com.vinio.sportapplication.bottomNavigation.notification.MyWorker
import com.vinio.sportapplication.bottomNavigation.notification.areNotificationsEnabledForChannel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.concurrent.TimeUnit

@Composable
fun AddEventPopup(onDismiss: () -> Unit, viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current

    val title = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    val status = remember { mutableStateOf(TextFieldValue("")) }
    val calories = remember { mutableStateOf(TextFieldValue("")) }
    val category = remember { mutableStateOf(TextFieldValue("")) }

    var startDate by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected("$dayOfMonth.${month + 1}.$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun showTimePicker(onTimeSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            context,
            { _, hour, minute ->
                onTimeSelected("$hour:$minute")
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

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
                                focusManager.moveFocus(FocusDirection.Down)
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


            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Начало:", fontWeight = FontWeight.Medium)
                    Text(
                        text = if (startDate.isNotEmpty()) startDate else "Выберите дату",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clickable { showDatePicker { startDate = it } },
                        color = if (startDate.isNotEmpty()) Color.Black else Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
//                    Text("Время начала:", fontWeight = FontWeight.Medium)
                    Text("", fontWeight = FontWeight.Medium)
                    Text(
                        text = if (startTime.isNotEmpty()) startTime else "Выберите время",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clickable { showTimePicker { startTime = it } },
                        color = if (startTime.isNotEmpty()) Color.Black else Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Окончание:", fontWeight = FontWeight.Medium)
                    Text(
                        text = if (endDate.isNotEmpty()) endDate else "Выберите дату",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clickable { showDatePicker { endDate = it } },
                        color = if (endDate.isNotEmpty()) Color.Black else Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
//                    Text("Время окончания:", fontWeight = FontWeight.Medium)
                    Text("", fontWeight = FontWeight.Medium)
                    Text(
                        text = if (endTime.isNotEmpty()) endTime else "Выберите время",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clickable { showTimePicker { endTime = it } },
                        color = if (endTime.isNotEmpty()) Color.Black else Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            inputField(title, "Введите название", titleFocusRequester)
            inputField(description, "Введите описание", descriptionFocusRequester)
            inputField(status, "Введите статус", titleFocusRequester)
            inputField(category, "Введите категорию", titleFocusRequester)
            inputField(calories, "Введите кол-во калорий", titleFocusRequester)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        val parsedStartTime = parseDateTime(startDate, startTime)
                        val parsedEndTime = parseDateTime(endDate, endTime)

                        if (parsedStartTime == null || parsedEndTime == null) {
                            println("Некорректные дата или время")
                            return@Button
                        }

                        val event = EventEntity(
                            id = 0L,
                            title = title.value.text,
                            description = description.value.text,
                            status = status.value.text,
                            startTime = parsedStartTime,
                            endTime = parsedEndTime,
                            calories = calories.value.text.toIntOrNull() ?: 0,
                            category = category.value.text,
                            createdAt = LocalDateTime.now(),
                            updatedAt = LocalDateTime.now()
                        )

                        viewModel.addEvent(event, context)
//                        if (!areNotificationsEnabledForChannel(context, "events_channel")) {
//                            Log.d("notification", "Уведомления о событиях отключены")
//                            return@Button
//                        }

                       /* val eventStartTimeMillis = event.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                        val currentTimeMillis = System.currentTimeMillis()
                        val timeToTriggerNotification = eventStartTimeMillis - (60 * 60 * 1000) - currentTimeMillis

                        if (timeToTriggerNotification > 0) {
                            val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                                .setInitialDelay(timeToTriggerNotification, TimeUnit.MILLISECONDS)
                                .build()
                            WorkManager.getInstance(context).enqueue(workRequest)
                        }*/
                        val currentTimeMillis = System.currentTimeMillis()
                        val timeToTriggerNotification = 10 * 1000 // 10 секунд

                        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                            .setInitialDelay(timeToTriggerNotification.toLong(), TimeUnit.MILLISECONDS)
                            .build()

                        WorkManager.getInstance(context).enqueue(workRequest)


                        onDismiss()
                    } catch (e: Exception) {
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

@SuppressLint("DefaultLocale")
fun parseDateTime(date: String, time: String): LocalDateTime? {
    return try {
        val correctedTime = time.split(":").let {
            String.format("%02d:%02d", it[0].toInt(), it[1].toInt())
        }
        val formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm")
        LocalDateTime.parse("$date $correctedTime", formatter)
    } catch (e: Exception) {
        println("Ошибка парсинга даты/времени: ${e.message}")
        null
    }
}

