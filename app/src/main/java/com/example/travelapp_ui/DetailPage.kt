package com.example.travelapp_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailScreen(title: String, navController: NavController) {
    val allDestinations = listOf(
        Destination(
            "Mount Fuji, Tokyo",
            "Tokyo, Japan",
            "4.8",
            R.drawable.mount_fuji,
            "Mount Fuji is Japan's tallest mountain and an iconic symbol of the country. Known for its almost perfectly symmetrical cone, Mount Fuji is a popular destination for hikers and photographers. The best time to climb is during summer, offering stunning sunrise views from the summit."
        ),
        Destination(
            "Andes Mountain",
            "South, America",
            "4.5",
            R.drawable.andes_mountain,
            "The Andes is the longest continental mountain range in the world, stretching through seven countries in South America. It offers dramatic landscapes, rich cultural history, and unique biodiversity. The Andes is home to iconic sites like Machu Picchu and is a favorite for adventure seekers and nature lovers."
        )
    )

    val destination = allDestinations.find { it.title == title }


    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        if (destination != null) {
            Column {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(24.dp))
                        .clip(RoundedCornerShape(24.dp))
                ) {
                    Image(
                        painter = painterResource(id = destination.image),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_left),
                                contentDescription = "Back Icon",
                                tint = Color(0XFFE1E1E1),
                                modifier = Modifier
                                    .background(color = Color(0xFF1D1D1D), CircleShape)
                                    .padding(8.dp)
                                    .size(15.dp)
                                    .clickable{
                                        navController.popBackStack()
                                    }
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.archive),
                                contentDescription = "Archive",
                                tint = Color(0XFFE1E1E1),
                                modifier = Modifier
                                    .background(color = Color(0xFF1D1D1D), CircleShape)
                                    .padding(8.dp)
                                    .size(15.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(220.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color(0xFF1D1D1D), shape = RoundedCornerShape(24.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        destination.title,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "Price", color = Color(0xFFCAC8C8),
                                        fontWeight = FontWeight.Normal,

                                        )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.fi_rr_marker),
                                            contentDescription = "Location",
                                            tint = Color.White,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(destination.location, color = Color.White)
                                    }
                                    Text("$230", color = Color.White)
                                }
                            }
                        }
                    }

                }

                Text(
                    "Overview",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    IconWithText(R.drawable.icon_clock, "8 hours")
                    IconWithText(R.drawable.icon_cloud, "16°C")
                    IconWithText(R.drawable.icon_star, "4.5")
                }
                Text(
                    destination.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                ButtonBook(onBookClick = {})
            }
        } else {
            Text("Destination Not Found")
        }

    }
}

@Composable
fun IconWithText(icon: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text)
    }
}


@Composable
fun ButtonBook(onBookClick: () -> Unit) {
    Button(
        onClick = onBookClick, colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Black
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Book Now", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = "Send Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


