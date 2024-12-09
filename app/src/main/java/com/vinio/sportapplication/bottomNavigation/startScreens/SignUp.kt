package com.vinio.sportapplication.bottomNavigation.startScreens

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinio.sportapplication.R
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.BottomButtons
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton

@Composable
fun SignUpScreen(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.header),
            contentDescription = "Лого",
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        SignUpElements(
            name = name,
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onRepeatPasswordChange = { repeatPassword = it },
            onButtonClick = {
                navController.navigate("main_screen") {
                    popUpTo("screen_signUn") { inclusive = true }
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomButtons(
            firstButtonText = "Впервые в сервисе?",
            secondButtonText = "Авторизация",
            onFirstClick = {Log.d("[TEST]", "test")},
            onSecondClick = {
                navController.navigate("sign_in")
                Log.d("[TEST]", "navigate to sign_in")
            }
        )
    }
}

@Composable
fun SignUpElements(
    name: String,
    email: String,
    password: String,
    repeatPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onButtonClick: () -> Unit,
) {

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
            onValueChange = onNameChange,
            placeholderText = "Ваше ФИО..."
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "Ваш e-mail...",
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholderText = "Введите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            placeholderText = "Повторите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .background(BlueButton)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("textField", name)
                    Log.d("textField", email)
                    Log.d("textField", password)
                    Log.d("textField", repeatPassword)
                    onButtonClick()
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


