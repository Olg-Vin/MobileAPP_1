package com.vinio.sportapplication.bottomNavigation.startScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.ui.theme.BlueButton
import com.vinio.sportapplication.ui.theme.BlueText

@Preview(showBackground = true)
@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Всего один шаг до спорта!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(120.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    color = BlueText,
                    shape = RoundedCornerShape(8.dp)
                ) // Граница с закруглением
                .background(Color.White),
            placeholder = {
                Text(
                    text = "Введите ваш e-mail...",
                    style = TextStyle(fontSize = 16.sp, color = BlueText)
                )
            },
        )

        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    color = BlueText,
                    shape = RoundedCornerShape(8.dp)
                ) // Граница с закруглением
                .background(Color.White),
            placeholder = {
                Text(
                    text = "Введите пароль...",
                    style = TextStyle(fontSize = 16.sp, color = BlueText)
                )
            },
        )

        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .background(BlueButton)
                .width(250.dp)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    // TODO
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Войти",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
