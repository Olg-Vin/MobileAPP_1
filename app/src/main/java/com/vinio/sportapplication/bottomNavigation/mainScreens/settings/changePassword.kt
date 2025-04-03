package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    navController: NavController = rememberNavController()
) {
    var password by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        SettingsButton(
            text = "Назад",
            onClick = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода текущего пароля
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholderText = "Ваш текущий пароль...",
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Поле ввода пароля
        CustomTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            placeholderText = "Введите новый пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Поле ввода повторного пароля
        CustomTextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            placeholderText = "Повторите новый пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(40.dp)) // Уменьшаем отступ перед кнопкой

        // Кнопка смены
        Box(
            modifier = Modifier
                .background(BlueButton)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("textField", password)
                    Log.d("textField", newPassword)
                    Log.d("textField", repeatPassword)

                    coroutineScope.launch {
                        val success = changeUserPassword(context, password, newPassword)
                        if (success) {
                            Toast.makeText(context, "Пароль успешно изменён", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Ошибка смены пароля", Toast.LENGTH_SHORT).show()
                        }
                    }
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Изменить пароль",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(60.dp))

    }
}