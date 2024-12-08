package com.vinio.sportapplication.bottomNavigation.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinio.sportapplication.bottomNavigation.startScreens.smallElements.CustomTextField

@Composable
fun ScreenSignIn(onClick: () -> Unit) {
    val message = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ScreenSignIn",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        TextField(
            value = message.value,
            onValueChange = {newText -> message.value = newText}
        )
        var email by remember { mutableStateOf("") }
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholderText = "Введите ваш e-mail..."
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {

            }
        ) {
            Text(text = "To sign up")
        }
    }
}

@Composable
fun ScreenSignUp(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ScreenSignUp",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onClick() }
        ) {
            Text(text = "To main (screen 1)")
        }
    }
}


@Composable
fun Screen1() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen1",
        textAlign = TextAlign.Center,
    )
}

@Composable
fun Screen2() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen2",
        textAlign = TextAlign.Center,
    )
}

@Composable
fun Screen3() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen3",
        textAlign = TextAlign.Center,
    )
}

@Composable
fun Screen4() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen4",
        textAlign = TextAlign.Center,
    )
}
