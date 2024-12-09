package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun UserDetailsScreen(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsButton(
        text = "Назад",
        onClick = { navController.popBackStack() }
        )
        SettingsText("","")


    }
}