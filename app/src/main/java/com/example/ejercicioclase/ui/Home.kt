package com.example.ejercicioclase.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme
import com.example.myapplication.R


class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioClaseTheme {
                Surface(){
                    Navigation()
                }
            }
        }
    }
}
@Composable
fun HomeScreen(navController : NavController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item{
                TopBar()
            }
            
            // Sección de Categorías
            item { SectionTitleWithAction("Categorías", "Ver más") }
            item { CategoriesRow() }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Banner Promocional
            item { PromoBanner() }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Productos en oferta
            item { SectionTitleWithAction("Ofertas del día", "Ver todo") }
            item { ProductCorrousel(navController) }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            // Otra sección de ejemplo
            item { SectionTitle("Novedades") }
            item { ProductCorrousel(navController) }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ){
            FloatingButton()
        }
    }
}

@Composable
fun SectionTitleWithAction(title: String, actionText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = actionText,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun CategoriesRow() {
    val categories = listOf("CBD", "Ropa", "Merch", "Ofertas", "Nuevo")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.size) { index ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = categories[index], fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun PromoBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            // Fondo oscuro simulado o imagen
            Surface(color = Color(0xFF1B5E20), modifier = Modifier.fillMaxSize()) {}
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "20% DESCUENTO",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
                Text(
                    text = "En tu primera compra de CBD",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White
                ) {
                    Text(
                        text = "USAR CÓDIGO: MERCH20",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCorrousel(navController: NavController? = null) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            Card(
                modifier = Modifier.width(160.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Producto CBD", fontWeight = FontWeight.Bold, maxLines = 1)
                        Text(text = "15.99€", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    EjercicioClaseTheme() {
        HomeScreen(rememberNavController())
    }
}

@Preview(showSystemUi = true, showBackground = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape"
)
@Composable
fun Preview2(){
    EjercicioClaseTheme() {
        HomeScreen(rememberNavController())
    }
}

@Composable
fun ProductSection(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(3){
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Sample product",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
            )
        }
    }
}

@Composable
fun FloatingButton(){
    FloatingActionButton(
        onClick = {
            //TODO Enviar a la promoción
        },
        containerColor = Color.Red,
        shape = CircleShape,
        modifier = Modifier
            .size(80.dp),

    ) {
        Text(
            text = "LA LIGA\nGRATIS",
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
    }
}

@Preview
@Composable
fun PreviewFloatingButton(){
    EjercicioClaseTheme() {
        FloatingButton()
    }
}