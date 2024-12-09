package com.vinio.sportapplication.bottomNavigation.startScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(100.dp))
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            placeholderText = "Ваше ФИО..."
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholderText = "Ваш e-mail...",
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholderText = "Введите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            placeholderText = "Повторите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .background(BlueButton)
                .width(250.dp)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("textField", name)
                    Log.d("textField", email)
                    Log.d("textField", password)
                    Log.d("textField", repeatPassword)
                    // TODO
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Зарегистрироваться",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
