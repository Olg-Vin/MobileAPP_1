package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vinio.sportapplication.bottomNavigation.mainScreens.Calculate.CalculateScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen3
import com.vinio.sportapplication.bottomNavigation.mainScreens.calendar.CalendarScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventListScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.ChangeEmailScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.ChangePasswordScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.SettingsScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.UserDataScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.settings.UserDetailsScreen
import com.vinio.sportapplication.bottomNavigation.notification.NotificationSettingsScreen
import com.vinio.sportapplication.bottomNavigation.startScreens.SignInScreen

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
            CalculateScreen()
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
        composable("user_password") {
            ChangePasswordScreen(navHostController)
        }
        composable("user_email") {
            ChangeEmailScreen(navHostController)
        }
        composable("main_screen") {
            SignInScreen(navHostController)
        }
        composable("notification_screen") {
            NotificationSettingsScreen(navHostController)
        }

    }
}



