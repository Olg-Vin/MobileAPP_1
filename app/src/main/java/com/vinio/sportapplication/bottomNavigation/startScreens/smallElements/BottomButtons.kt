package com.vinio.sportapplication.bottomNavigation.startScreens.smallElements

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.ui.theme.BlueButton

@Composable
fun BottomButtons(
    firstButtonText: String,
    secondButtonText: String,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .border(1.dp, BlueButton)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable{
                    onFirstClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstButtonText,
                fontSize = 16.sp,
                color = BlueButton,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .border(1.dp, BlueButton)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable{
                    onSecondClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = secondButtonText,
                fontSize = 16.sp,
                color = BlueButton,
                fontWeight = FontWeight.Bold
            )
        }
    }
}