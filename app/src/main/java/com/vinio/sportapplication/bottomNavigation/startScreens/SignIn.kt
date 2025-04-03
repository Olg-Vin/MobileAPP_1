package com.vinio.sportapplication.bottomNavigation.startScreens

import android.content.Context
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinio.sportapplication.R
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.loginUser
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.BottomButtons
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton
import kotlinx.coroutines.launch

@Composable
fun SignInElements(
    context: Context,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navController: NavController, // Pass NavController to this composable
    isError: Boolean,            // Pass isError state as a parameter
    onError: (Boolean) -> Unit   // Pass a callback to update isError state
) {
    // Create a coroutine scope to launch coroutines
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(horizontal = 16.dp), // Добавим горизонтальные отступы
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Заголовок "Всего один шаг до спорта!"
        Text(
            text = "Всего один шаг до спорта!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp, // Размер шрифта
            lineHeight = 48.sp, // Увеличиваем межстрочный интервал (можно настроить)
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Поля ввода e-mail и пароля
        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "Введите ваш e-mail..."
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholderText = "Введите ваш пароль...",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Кнопка "Войти"
        Box(
            modifier = Modifier
                .background(BlueButton)
                .height(50.dp)
                .fillMaxWidth()
                .clickable {
                    // Launch the coroutine to call the suspend function
                    coroutineScope.launch {
                        val response = loginUser(context, email, password)
                        Log.d("entry", response.toString())
                        if (response.accessToken != "") {
                            // Если вход успешен, переходим на главный экран
                            navController.navigate("main_screen") {
                                popUpTo("screen_signIn") { inclusive = true }
                            }
                        } else {
                            // Если ошибка входа, показываем сообщение об ошибке
                            onError(true)
                        }
                    }
                }
                .padding(horizontal = 16.dp), // Добавим немного padding
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Войти",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(60.dp)) // Пробел перед нижними кнопками
    }
}


@Composable
fun SignInScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("testUser0@test.ru") }
    var password by remember { mutableStateOf("123456") }
    var isError by remember { mutableStateOf(false) } // Для отображения ошибки при неверных данных
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Это изменяет распределение элементов
    ) {
        // Логотип в верхней части экрана
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.header),
            contentDescription = "Лого",
            modifier = Modifier
                .padding(top = 40.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(80.dp))

        // Элементы для ввода данных
        SignInElements(
            context = context,
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            navController = navController,
            isError = isError,
            onError = { isError = it }
        )

        Spacer(modifier = Modifier.weight(1f)) // Пробел между основным контентом и кнопками

        // Отображение ошибки при неверном вводе данных
        if (isError) {
            Text(
                text = "Неверный логин или пароль",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }

        // Кнопки для "Забыли пароль?" и "Регистрация"
        BottomButtons(
            firstButtonText = "Забыли пароль?",
            secondButtonText = "Регистрация",
            onFirstClick = {
                navController.navigate("forgot_password")
                Log.d("[SignIn]", "navigate to forgot_password")
            },
            onSecondClick = {
                navController.navigate("sign_up")
                Log.d("[SignIn]", "navigate to sign_up")
            }
        )
    }
}



