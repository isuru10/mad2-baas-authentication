package com.example.authexample.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authexample.data.models.AuthState
import com.example.authexample.data.service.FirebaseAuthServiceImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val authService = FirebaseAuthServiceImpl()

    // Exposes the AuthState flow to the UI for observation
    val authState: StateFlow<AuthState> = authService.authState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthState.Loading // Initial state
        )

    fun handleLogin(email: String, password: String) {
        viewModelScope.launch {
            // Service handles the actual sign-in and updates the shared authState flow
            authService.login(email, password)
        }
    }

    fun handleSignup(email: String, password: String) {
        viewModelScope.launch {
            authService.signUp(email, password)
        }
    }

    fun handleLogout() {
        viewModelScope.launch {
            authService.signOut()
        }
    }
}