package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.R

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
        }
    ) {
        NavGraph(navHostController = navController)
    }
}

