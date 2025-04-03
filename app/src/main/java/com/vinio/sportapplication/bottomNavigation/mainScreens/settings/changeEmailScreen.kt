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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton
import kotlinx.coroutines.launch

@Composable
fun ChangeEmailScreen(navController: NavController = rememberNavController()) {
    var oldEmail by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var repeatEmail by remember { mutableStateOf("") }
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

        CustomTextField(
            value = oldEmail,
            onValueChange = { oldEmail = it },
            placeholderText = "Ваш текущий e-mail...",
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = newEmail,
            onValueChange = { newEmail = it },
            placeholderText = "Введите новый e-mail...",
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = repeatEmail,
            onValueChange = { repeatEmail = it },
            placeholderText = "Повторите новый e-mail...",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .background(BlueButton)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    if (newEmail != repeatEmail) {
                        Log.e("ChangeEmailScreen", "Email-адреса не совпадают")
                        return@clickable
                    }

                    coroutineScope.launch {
                        val success = changeUserEmail(context, oldEmail, newEmail)
                        if (success) {
                            Toast.makeText(context, "Email изменён. Перезайдите в аккаунт", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Ошибка смены email", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Изменить email",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

