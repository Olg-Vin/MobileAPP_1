package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventListScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen2
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen3
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen4
import com.vinio.sportapplication.bottomNavigation.mainScreens.calendar.CalendarScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.SettingsScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.UserDataScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.UserDetailsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "screen_1") {

        composable("screen_1") {
            EventListScreen()
        }
        composable("screen_2") {
            CalendarScreen()
        }
        composable("screen_3") {
            Screen3()
        }
        composable("screen_4") {
            SettingsScreen(navHostController)
        }
        composable("user_data") {
            UserDataScreen(navHostController)
        }
        composable("user_detail") {
            UserDetailsScreen(navHostController)
        }
    }
}