package com.example.travelapp_ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomRoutes = listOf("home", "history", "favorite", "profile")

    Scaffold(bottomBar = {
        if (currentRoute in bottomRoutes) {
            BottomNavigationBar(navController = navController)
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("welcome") {
                WelcomeScreen(navController = navController)
            }
            composable("home") {
                HomePageScreen(navController = navController)
            }
            composable("history") {
                HomePageScreen(navController = navController)
            }
            composable("favorite") {
                WelcomeScreen(navController = navController)
            }
            composable("profile") {
                HomePageScreen(navController = navController)
            }
            composable("detail/{title}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                DetailScreen(title = title, navController)
            }
        }

    }
}