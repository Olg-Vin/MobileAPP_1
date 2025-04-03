package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun SettingsScreen(
    navController: NavController
){
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsButton(
            text = "Личные данные",
            onClick = { navController.navigate("user_data") }
        )
        SettingsButton(
            text = "Уведомления",
            onClick = { navController.navigate("notification_screen") }
        )
        SettingsButton(
            text = "Связь с устройством",
            onClick = { Toast.makeText(context, "Будет позже", Toast.LENGTH_SHORT).show() }
        )
        SettingsButton(
            text = "Выйти",
            onClick = { Toast.makeText(context, "Выхода нет", Toast.LENGTH_SHORT).show() }
        )
    }
}
