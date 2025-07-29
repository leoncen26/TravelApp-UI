package com.example.travelapp_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.savedstate.savedState

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object History : BottomNavItem("history", Icons.Default.AccessTime, "History")
    object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorite")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.History,
        BottomNavItem.Favorite,
        BottomNavItem.Profile
    )
    BottomNavigation(backgroundColor = Color.White, modifier = Modifier.navigationBarsPadding()) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(item.icon, contentDescription = item.label)
                        if (currentDestination?.route == item.route) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(6.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color.Red)
                            )
                        }
                    }
                },
//                label = { Text(item.label) }
            )
        }
    }
}