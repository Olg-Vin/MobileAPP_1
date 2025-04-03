package com.vinio.sportapplication.bottomNavigation.mainScreens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vinio.sportapplication.bottomNavigation.entity.UserData
import com.vinio.sportapplication.ui.theme.BlueButton

@Preview
@Composable
fun UserDetailsScreen(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    var userData by remember { mutableStateOf<UserData?>(null) }

    LaunchedEffect(Unit) {
        userData = fetchUserData(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        SettingsButton(
            text = "Назад",
            onClick = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        userData?.let { user ->
            UserInfoRow("Имя:", user.username)
            UserInfoRow("Email:", user.email)
            UserInfoRow("Дата создания:", user.createdAt.toString().substring(0, 10))
            UserInfoRow("Дата обновления:", user.updatedAt.toString().substring(0, 10))
        } ?: run {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun UserInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Отступы справа и слева
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Добавляем вертикальные отступы
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(text = value, fontWeight = FontWeight.Bold)
        }
        Divider(color = BlueButton, thickness = 1.dp) // Подчеркивание
    }
}

