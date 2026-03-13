package com.example.ejercicioclase.vm

import com.example.ejercicioclase.ui.PaymentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object PaymentVM {
    private val _uiState = MutableStateFlow(PaymentState())
    val uiState: StateFlow<PaymentState> = _uiState.asStateFlow()

    fun onFullNameChange(name: String) {
        _uiState.update { it.copy(fullName = name) }
    }

    fun onCreditCardChange(card: String) {
        _uiState.update { it.copy(creditCard = card.toIntOrNull() ?: 0) }
    }

    fun onExpirationDateChange(date: String) {
        _uiState.update { it.copy(expirationDate = date.toIntOrNull() ?: 0) }
    }

    fun onCVVChange(cvv: String) {
        _uiState.update { it.copy(CVV = cvv.toIntOrNull() ?: 0) }
    }
}