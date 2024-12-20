package com.vinio.sportapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.startScreens.StartNavGraph
import com.vinio.sportapplication.ui.theme.SportApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SportApplicationTheme {
                StartNavGraph(navController)
                /*NavHost(navController = navController, startDestination = "screen_signIn") {
                    composable("screen_signIn") {
                        ScreenSignIn {
                            navController.navigate("screen_signUp")
                        }
                    }
                    composable("screen_signUp") {
                        ScreenSignUp {
                            navController.navigate("main_screen") {
                                popUpTo("screen_signIn") { inclusive = true }
                            }
                        }
                    }
                    composable("main_screen") {
                        MainScreen()
                    }
                }*/
            }
        }
    }
}
