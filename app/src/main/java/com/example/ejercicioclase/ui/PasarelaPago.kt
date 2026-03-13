package com.example.ejercicioclase.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ejercicioclase.vm.CarritoVM
import com.example.ejercicioclase.vm.PaymentVM

@Composable
fun PaymentScreen(navController: NavController) {
    val uiState by PaymentVM.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Pasarela de Pago",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campos de texto normales como pediste
        TextField(
            value = uiState.fullName,
            onValueChange = { PaymentVM.onFullNameChange(it) },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = if (uiState.creditCard == 0) "" else uiState.creditCard.toString(),
            onValueChange = { PaymentVM.onCreditCardChange(it) },
            label = { Text("Número de tarjeta") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            TextField(
                value = if (uiState.expirationDate == 0) "" else uiState.expirationDate.toString(),
                onValueChange = { PaymentVM.onExpirationDateChange(it) },
                label = { Text("Fecha Exp.") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = if (uiState.CVV == 0) "" else uiState.CVV.toString(),
                onValueChange = { PaymentVM.onCVVChange(it) },
                label = { Text("CVV") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total a pagar:", fontSize = 18.sp)
            Text(
                text = "${CarritoVM.calculateTotal()}€",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(
            onClick = {
                if (uiState.fullName.isNotBlank() && uiState.creditCard != 0) {
                    Toast.makeText(context, "¡Pago realizado con éxito!", Toast.LENGTH_LONG).show()
                    CarritoVM.clearCart()
                    navController.navigate("Home") {
                        popUpTo("Home") { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, "Por favor, rellena los datos de pago", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("PAGAR AHORA")
        }
    }
}