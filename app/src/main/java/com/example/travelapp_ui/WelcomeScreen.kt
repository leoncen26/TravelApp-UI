package com.example.travelapp_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0172B2),
                        Color(0xFF001645)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text("Travel", style = TextStyle(color = Color.White, fontSize = 44.sp))
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painter = painterResource(id = R.drawable.globe_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(44.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Find Your Dream \n Destination With Us",
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(250.dp))
        Button(
            onClick = {navController.navigate("home")},
            modifier = Modifier
                .size(300.dp, 70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("Get Started", style = TextStyle())
        }
    }
}