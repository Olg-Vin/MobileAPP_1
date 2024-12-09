package com.vinio.sportapplication.bottomNavigation.mainScreens.navigation

import com.vinio.sportapplication.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1: BottomItem("Дом", R.drawable.image_1, "screen_1")
    object Screen2: BottomItem("Календарь", R.drawable.image_2, "screen_2")
    object Screen3: BottomItem("Еда", R.drawable.image_3, "screen_3")
    object Screen4: BottomItem("Настройки", R.drawable.image_4, "screen_4")
}