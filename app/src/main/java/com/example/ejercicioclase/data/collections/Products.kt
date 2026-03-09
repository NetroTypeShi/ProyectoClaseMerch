package com.example.ejercicioclase.data.collections

import com.example.ejercicioclase.data.Categoria
import com.example.ejercicioclase.data.Category
import com.example.ejercicioclase.data.Product
import com.example.myapplication.R
import java.util.Locale

object Products {
    val productList = listOf<Product>(
        Product(image = R.drawable.ic_launcher_foreground, name = "Product1", price = "10$", category = Category.CBD, id = 1),
        Product(image = R.drawable.ic_launcher_foreground, name = "Product2", price = "20$", category = Category.THERIAN, id = 2),
        Product(image = R.drawable.ic_launcher_foreground, name = "Product3", price = "30$", category = Category.LORAZEPAN, id = 3),

    )
}