package com.vinio.sportapplication.bottomNavigation.mainScreens

import com.vinio.sportapplication.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1: BottomItem("Дом", R.drawable.icon, "screen_1")
    object Screen2: BottomItem("Календарь", R.drawable.icon, "screen_2")
    object Screen3: BottomItem("Еда", R.drawable.icon, "screen_3")
    object Screen4: BottomItem("Настройки", R.drawable.icon, "screen_4")
}