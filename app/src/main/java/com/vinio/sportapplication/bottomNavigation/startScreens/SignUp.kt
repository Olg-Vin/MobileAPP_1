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
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.registerUser
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.BottomButtons
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField
import com.vinio.sportapplication.ui.theme.BlueButton
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) } // Для отображения ошибки при неверных данных
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Логотип в верхней части экрана
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.header),
            contentDescription = "Лого",
            modifier = Modifier
                .padding(top = 40.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(40.dp)) // Меньше отступ между логотипом и формой
        SignUpElements(
            context = context,
            name = name,
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onRepeatPasswordChange = { repeatPassword = it },
            navController = navController,
            isError = isError,
            onError = { isError = it }
        )
        Spacer(modifier = Modifier.weight(1f)) // Используем остаток пространства для нижней секции
        BottomButtons(
            firstButtonText = "Впервые в сервисе?",
            secondButtonText = "Авторизация",
            onFirstClick = {
//                TODO создать страницу первый_раз_в_сервисе
//                navController.navigate("on_first")
                Log.d("[SignUp]", "test")
           },
            onSecondClick = {
                navController.navigate("sign_in")
                Log.d("[SignUp]", "navigate to sign_in")
            }
        )
    }
}

@Composable
fun SignUpElements(
    context: Context,
    name: String,
    email: String,
    password: String,
    repeatPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    navController: NavController, // Pass NavController to this composable
    isError: Boolean,            // Pass isError state as a parameter
    onError: (Boolean) -> Unit   // Pass a callback to update isError state
) {
    // Create a coroutine scope to launch coroutines
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .width(300.dp) // Ограничиваем ширину формы
            .padding(horizontal = 16.dp), // Добавляем горизонтальные отступы
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


        Spacer(modifier = Modifier.height(40.dp)) // Уменьшаем отступ до полей ввода

        // Поле ввода имени
        CustomTextField(
            value = name,
            onValueChange = onNameChange,
            placeholderText = "Ваше ФИО..."
        )

        Spacer(modifier = Modifier.height(20.dp)) // Уменьшаем отступ между полями

        // Поле ввода e-mail
        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "Ваш e-mail...",
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Поле ввода пароля
        CustomTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholderText = "Введите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Поле ввода повторного пароля
        CustomTextField(
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            placeholderText = "Повторите пароль...",
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(40.dp)) // Уменьшаем отступ перед кнопкой

        // Кнопка регистрации
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
                    // Launch the coroutine to call the suspend function
                    coroutineScope.launch {
                        val response = registerUser(context, email, password)
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
        Spacer(modifier = Modifier.height(60.dp))
    }
}
