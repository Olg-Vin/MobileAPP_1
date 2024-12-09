package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vinio.sportapplication.ui.theme.BlueButton

@Composable
fun CustomBottomNav(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen3,
        BottomItem.Screen4,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // Высота навигации
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly, // Равномерное распределение кнопок
        verticalAlignment = Alignment.CenterVertically
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            Box( // Используем Box для кнопок
                modifier = Modifier
                    .weight(1f) // Каждая кнопка занимает равное пространство
                    .fillMaxHeight() // Кнопки растягиваются на всю высоту родителя
                    .clickable { navController.navigate(item.route) }
                    .background(if (currentRoute == item.route) BlueButton else Color.White)
                    .border(1.dp, BlueButton), // Добавляем голубую границу
                contentAlignment = Alignment.Center // Центруем содержимое внутри кнопки
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    contentDescription = "base_icon",
                    modifier = Modifier.size(40.dp), // Размер иконки внутри кнопки
                    tint = if (currentRoute == item.route) Color.White else Color.Black
                )
            }
        }
    }


}
