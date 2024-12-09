package com.vinio.sportapplication.bottomNavigation.startScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.R
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.BottomButtons
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton

//@Preview
@Composable
fun SignInScreen(
    navController: NavController
) {
//    val navController = rememberNavController()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.height(30.dp))
        SignInElements(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onButtonClick = {
                navController.navigate("main_screen") {
                    popUpTo("screen_signIn") { inclusive = true }
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomButtons(
            firstButtonText = "Забыли пароль?",
            secondButtonText = "Регистрация",
            onFirstClick = {
                navController.navigate("forgot_password")
                Log.d("[TEST]", "test")
            },
            onSecondClick = {
                navController.navigate("sign_up")
                Log.d("[TEST]", "test")
            }
        )
    }
}

@Composable
fun SignInElements(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonClick: () -> Unit
){
    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White),
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
        CustomTextField(
            value = email,
            onValueChange = onEmailChange, // Обновляем email
            placeholderText = "Введите ваш e-mail..."
        )

        Spacer(modifier = Modifier.height(40.dp))
        CustomTextField(
            value = password,
            onValueChange = onPasswordChange, // Обновляем password
            placeholderText = "Введите ваш пароль...",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .background(BlueButton)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("textField", email)
                    Log.d("textField", password)
                    onButtonClick()
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

        Spacer(modifier = Modifier.height(160.dp))
    }
}