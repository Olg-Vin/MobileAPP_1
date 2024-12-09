package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.R
import com.vinio.sportapplication.bottomNavigation.mainScreens.calendar.AddEventPopup

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            CustomBottomNav(navController = navController)
        },
        topBar = {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.header),
                contentDescription = "Лого института",
                modifier = Modifier.padding(top = 20.dp)
            )
        },
        /*floatingActionButton = {
            var showPopup by remember { mutableStateOf(false) }
            FloatingActionButton(
                onClick = { showPopup = true },
                modifier = Modifier
                    .padding(16.dp),  // Отступы от краев экрана
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить")
            }


            // Открытие Popup
            if (showPopup) {
                AddEventPopup(onDismiss = { showPopup = false })
            }
        }*/
    ) {
        NavGraph(navHostController = navController)
    }
}