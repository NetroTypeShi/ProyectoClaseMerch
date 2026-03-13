package com.example.ejercicioclase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ejercicioclase.data.collections.Products
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme
import com.example.ejercicioclase.vm.CarritoVM

@Composable
fun CartScreen(navController: NavController) {
    val itemsInCart = CarritoVM.productsCart.toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mi Carrito",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (itemsInCart.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "El carrito está vacío")
                Button(onClick = { navController.navigate("Home") }, modifier = Modifier.padding(top = 16.dp)) {
                    Text("Ir a comprar")
                }
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(itemsInCart) { (id, quantity) ->
                    val product = Products.productList.find { it.id == id }
                    if (product != null) {
                        CartItemRow(product.name, product.price, quantity, id, product.image)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total:", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "${CarritoVM.calculateTotal()}€",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Button(
                onClick = { /* Pasarela de pago */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Finalizar Compra")
            }
        }
    }
}

@Composable
fun CartItemRow(name: String, price: String, quantity: Int, id: Int, image: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = image),
                contentDescription = name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Fila con Columna de textos (título y precio)
            Row(modifier = Modifier.weight(1f)) {
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                    Text(text = price, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }

            // Fila aparte con Sumar, Cantidad, Restar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = { CarritoVM.addProduct(id) }, modifier = Modifier.size(30.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Sumar")
                }
                Text(text = "$quantity", style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = { CarritoVM.removeProduct(id) }, modifier = Modifier.size(30.dp)) {
                    // Si Icons.Default.Remove no existe, usamos un texto simple para restar
                    Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Icono de borrar aparte (Papelera)
            IconButton(onClick = { CarritoVM.productsCart.remove(id) }, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar todo", tint = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    EjercicioClaseTheme {
        CartScreen(navController = rememberNavController())
    }
}