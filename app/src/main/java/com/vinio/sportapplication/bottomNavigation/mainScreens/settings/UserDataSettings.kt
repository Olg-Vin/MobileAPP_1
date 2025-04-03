package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun UserDataScreen(
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsButton(
            text = "Данные текущего пользователя",
            onClick = { navController.navigate("user_detail") }
        )
        SettingsButton(
            text = "Изменить пароль",
            onClick = { navController.navigate("user_password") }
        )
        SettingsButton(
            text = "Изменить электронную почту",
            onClick = { navController.navigate("user_email") }
        )
        SettingsButton(
            text = "Удалить аккаунт",
            onClick = {
                coroutineScope.launch {
                    val success = deleteAccount(context)
                    if (success) {
                        Toast.makeText(context, "Аккаунт удалён", Toast.LENGTH_SHORT).show()
//                        TODO надо как-то удалять я хз, можно просто приложение убить
                        /*navController.navigate("main_screen") { // Переход на главный экран
                            popUpTo(0) // Очистка истории навигации
                        }*/
                    } else {
                        Toast.makeText(context, "Ошибка удаления аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        )
        SettingsButton(
            text = "Назад",
            onClick = { navController.popBackStack() }
        )
    }
}