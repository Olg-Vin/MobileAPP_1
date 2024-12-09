package com.vinio.sportapplication.bottomNavigation.mainScreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale



@Composable
fun DateSelectorScreen(
    selectedDate: LocalDate,
    onDateChanged: (LocalDate) -> Unit
) {
    DateSelector(
        currentDate = getFormattedDate(selectedDate),
        onPreviousClick = {
            onDateChanged(selectedDate.minusDays(1)) // Уменьшаем на 1 день
        },
        onNextClick = {
            onDateChanged(selectedDate.plusDays(1)) // Увеличиваем на 1 день
        }
    )
}

// Функция для форматирования даты в строку
fun getFormattedDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale("ru"))
    return date.format(formatter)
}

@Composable
fun DateSelector(
    currentDate: String,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Кнопка "влево"
        IconButton(onClick = { onPreviousClick() }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Предыдущий день"
            )
        }

        // Карточка с текстом
        Card(
            modifier = Modifier.padding(horizontal = 8.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp
        ) {
            Text(
                text = currentDate,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // Кнопка "вправо"
        IconButton(onClick = { onNextClick() }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Следующий день"
            )
        }
    }
}
