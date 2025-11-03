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
    val authState: StateFlow<AuthState> = TODO("Expose the AuthState flow to the UI for observation")

    fun handleLogin(email: String, password: String) {
        TODO("Use the AuthService to handle the login")
    }

    fun handleSignup(email: String, password: String) {
        TODO("Use the AuthService to handle the signup")
    }

    fun handleLogout() {
        TODO("Use the AuthService to handle the logout")
    }
}