package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun UserDataScreen(
    navController: NavController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsButton(
            text = "Данные текущего пользователя",
            onClick = { /*TODO*/ }
        )
        SettingsButton(
            text = "Изменить пароль",
            onClick = { /*TODO*/ }
        )
        SettingsButton(
            text = "Изменить электронную почту",
            onClick = { /*TODO*/ }
        )
        SettingsButton(
            text = "Удалить аккаунт",
            onClick = { /*TODO*/ }
        )
        SettingsButton(
            text = "Назад",
            onClick = { navController.popBackStack() }
        )
    }
}