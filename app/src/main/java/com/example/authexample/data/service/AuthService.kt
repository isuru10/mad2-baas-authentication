package com.example.authexample.data.service

import com.example.authexample.data.models.AuthState
import kotlinx.coroutines.flow.Flow

interface AuthService {
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signOut()
    fun getUserId(): String

    val authState: Flow<AuthState>
}