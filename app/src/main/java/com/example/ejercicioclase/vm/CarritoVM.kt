package com.example.ejercicioclase.vm

import com.example.ejercicioclase.data.collections.Products
import java.util.TreeMap

/**
 * Singleton para gestionar el carrito de la compra.
 */
object CarritoVM {
    // ID del producto -> Cantidad
    val productsCart = TreeMap<Int, Int>()

    fun addProduct(id: Int) {
        val cantidadActual = productsCart[id] ?: 0
        productsCart[id] = cantidadActual + 1
    }

    fun removeProduct(id: Int) {
        val cantidadActual = productsCart[id] ?: 0
        if (cantidadActual > 1) {
            productsCart[id] = cantidadActual - 1
        } else {
            productsCart.remove(id)
        }
    }

    fun clearCart() = productsCart.clear()

    fun calculateTotal(): Double {
        var total = 0.0
        for ((id, cantidad) in productsCart) {
            val p = Products.productList.find { it.id == id }
            // Limpia el precio y lo suma
            val precio = p?.price?.replace("$", "")?.toDoubleOrNull() ?: 0.0
            total += precio * cantidad
        }
        return total
    }
}
