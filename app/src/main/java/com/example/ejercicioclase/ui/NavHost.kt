package com.example.ejercicioclase.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    val navController = rememberNavController()

    Scaffold(
        //bottomBar = { BottomNav(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("Home"){
                HomeScreen(navController)
            }
            composable("ProductHistory"){
                ProductHistoryScreen()
            }
            composable("Cart"){
                CartScreen(navController)
            }
            composable("Profile"){
                ProfileScreen()
            }
            composable("LaLiga"){
                LaLigaScreen()
            }
            composable("Mascot"){
                MascotScreen()
            }
            composable(
                route = "ProductDetail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId")
                ProductDetailScreen(navController, productId)
            }
        }
    }
}