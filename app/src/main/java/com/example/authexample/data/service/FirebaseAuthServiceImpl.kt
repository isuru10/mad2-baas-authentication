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
        // Attach the persistent listener on initialization
        auth.addAuthStateListener { firebaseAuth ->
            _authState.value = firebaseAuth.currentUser?.let { user ->
                // Idiomatic Kotlin check for non-null user
                AuthState.Authenticated(userId = user.uid, email = user.email)
            } ?: AuthState.Unauthenticated
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun signOut() {
        auth.signOut()
    }

    override fun getUserId(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("User not authenticated.")
    }
}