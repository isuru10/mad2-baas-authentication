package com.example.authexample.data.service

import com.example.authexample.data.models.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAuthServiceImpl : AuthService {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    override val authState = _authState.asStateFlow()

    private val auth = FirebaseAuth.getInstance()


    init {
        TODO("Attach the persistent listener on initialization")
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        TODO("Create user with email and password")
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        TODO("Sign in with email and password")
    }


    override suspend fun signOut() {
        TODO("Sign Out")
    }

    override fun getUserId(): String {
        TODO("Get user id")
    }
}