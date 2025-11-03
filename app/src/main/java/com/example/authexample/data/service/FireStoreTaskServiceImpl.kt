package com.example.authexample.data.service

import com.example.authexample.data.models.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MemoryCacheSettings
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

class FireStoreTaskServiceImpl : TaskService {

    val settings = FirebaseFirestoreSettings.Builder()
        .setLocalCacheSettings(MemoryCacheSettings.newBuilder().build())
        .build()

    private val db = FirebaseFirestore.getInstance().apply {
        firestoreSettings = settings
    }

    override suspend fun addTask(title: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun getRealtimeTasks(): Flow<List<Task>> = callbackFlow {
        TODO("Get realtime tasks with snapshot listener")
    }


}