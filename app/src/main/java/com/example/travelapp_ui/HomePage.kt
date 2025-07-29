package com.example.travelapp_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
@Preview
fun HomePagePreview() {
    HomePageScreen(navController = rememberNavController())
}

@Composable
fun HomePageScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Hi, David \uD83D\uDC4B",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                )
                Text(
                    "Explore the world",
                    style = TextStyle(
                        color = Color(0xFF888888),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar("Search...", onChangeSearched = {})
        Spacer(modifier = Modifier.height(40.dp))
        PopularText()
        Spacer(modifier = Modifier.height(30.dp))
        CategoryTabs(navController = navController)
    }
}


@Composable
fun SearchBar(hint: String = "", onChangeSearched: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(width = 1.dp, color = Color(0xFFD2D2D2), shape = RoundedCornerShape(30))
            .background(color = Color.Transparent, shape = RoundedCornerShape(50))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        TextField(
            value = hint, onValueChange = onChangeSearched, placeholder = {
                Text(hint, color = Color.Gray)

            },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 1,
            singleLine = true
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_setting),
            contentDescription = "Icon Setting",
            modifier = Modifier.size(24.dp, 22.dp)
        )
    }
}

@Composable
fun PopularText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Popular places",
            style = TextStyle(
                color = Color(0xFF2F2F2F),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            "view all",
            style = TextStyle(
                color = Color(0xFF888888),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun CategoryTabs(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Most Viewed") }

    val tabs = listOf("Most Viewed", "Nearby", "Latest")
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            tabs.forEach { tab ->
                val isSelectedTab = tab == selectedTab
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier

                        .background(
                            if (isSelectedTab) Color.Black else Color(0xFFFBFBFB),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            selectedTab = tab
                        }
                        .padding(horizontal = 14.dp, vertical = 16.dp)
                ) {
                    Text(
                        tab,
                        color = if (isSelectedTab) Color.White else Color.Gray,
                        fontWeight = if (isSelectedTab) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }
        when (selectedTab) {
            "Most Viewed" -> MostViewedContent(navController)
            "Nearby" -> NearbyContent(navController)
            "Latest" -> LatestContent(navController)
        }
    }
}


@Composable
fun MostViewedContent(navController: NavController) {
    val destinations = listOf(
        Destination("Mount Fuji, Tokyo", "Tokyo, Japan", "4.8", R.drawable.mount_fuji, "Mount Fuji"),
        Destination("Andes Mountain", "South, America", "4.5", R.drawable.andes_mountain, "Mount Fuji")
    )
    LazyRow() {
        items(destinations) { destination ->
           DestionationCard(destination, navController)
        }
    }
}

@Composable
fun NearbyContent(navController: NavController) {
    val destinations = listOf(
        Destination("Andes Mountain", "South, America", "4.5", R.drawable.andes_mountain, "Mount Fuji"),
        Destination("Mount Fuji, Tokyo", "Tokyo, Japan", "4.8", R.drawable.mount_fuji, "Mount Fuji")
    )
    LazyRow() {
        items(destinations) { destination ->
            DestionationCard(destination, navController)
        }
    }
}

@Composable
fun LatestContent(navController: NavController){
    val destinations = listOf(
        Destination("Mount Fuji, Tokyo", "Tokyo, Japan", "4.8", R.drawable.mount_fuji, "Mount Fuji"),
    )
    LazyRow() {
        items(destinations) { destination ->
            DestionationCard(destination, navController)
        }
    }
}


@Composable
fun DestionationCard(destination: Destination, navController: NavController) {
    Box(
        modifier = Modifier
            .size(width = 275.dp, height = 375.dp)
            .clip(RoundedCornerShape(24.dp)).padding(16.dp).clickable{
                navController.navigate("detail/${destination.title}")
            },
        ) {
        Image(
            painter = painterResource(id = destination.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.White
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .background(color = Color(0xFF1D1D1D), shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Text(
                    destination.title, style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(
                            destination.location, style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(
                            destination.rating, style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}