package com.vinio.sportapplication.bottomNavigation.startScreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen2
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen3
import com.vinio.sportapplication.bottomNavigation.mainScreens.Screen4
import com.vinio.sportapplication.bottomNavigation.mainScreens.home.EventListScreen
import com.vinio.sportapplication.bottomNavigation.mainScreens.navigation.MainScreen

@Composable
fun StartNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "sign_in") {
        composable("sign_in") {
            SignInScreen(navHostController)
        }
        composable("sign_up") {
            SignUpScreen(navHostController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen()
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}