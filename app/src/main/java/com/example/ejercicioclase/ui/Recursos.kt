package com.example.ejercicioclase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R

@Composable
fun TopBar(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier
                .height(64.dp)
                .width(64.dp),
        )

        SearchField("Buscar...")

        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Menu",
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
        )
    }
}

@Composable
fun SearchField(label : String, modifier: Modifier = Modifier){
    var search by remember { mutableStateOf("") }

    TextField(
        value = search,
        onValueChange = {search = it},
        label = { Text(text = label) },
        singleLine = true,
    )
}

@Composable
fun SectionTitle(title : String){
    Text(
        text = title,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BottomNav(navController : NavController, modifier: Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "Home",
            onClick = {
                navController.navigate("Home") {
                    popUpTo("Home") { inclusive = true }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Historial") },
            label = { Text("Historial") },
            selected = currentRoute == "ProductHistory",
            onClick = {
                navController.navigate("ProductHistory")
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = currentRoute == "Cart",
            onClick = {
                navController.navigate("Cart")
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = "La Liga") },
            label = { Text("La Liga") },
            selected = currentRoute == "LaLiga",
            onClick = {
                navController.navigate("LaLiga")
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = currentRoute == "Profile",
            onClick = {
                navController.navigate("Profile")
            }
        )
    }
}