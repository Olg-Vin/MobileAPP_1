package com.vinio.sportapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.startScreens.StartNavGraph
import com.vinio.sportapplication.ui.theme.SportApplicationTheme

/**
 * Точка входа в приложение
 * начинает со стартового графа - регистрация, авторизация.
 * */
// TODO поработать над кешированием авторизации
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SportApplicationTheme {
                StartNavGraph(navController)
            }
        }
    }
}


