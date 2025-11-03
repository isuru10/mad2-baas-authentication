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
        val tasksCollection = db.collection("tasks")

        val listener = tasksCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // Cancel the flow and propagate the error
                cancel(CancellationException("Firestore listener failed", error))
                return@addSnapshotListener
            }

            val tasks = snapshot?.documents?.map { doc ->
                Task(
                    id = doc.id,
                    title = doc.getString("title") ?: "Untitled",
                    isComplete = doc.getBoolean("isComplete") ?: false
                )
            } ?: emptyList()

            trySend(tasks)
        }

        awaitClose {
            // Crucial: Remove the listener to prevent memory leaks and unnecessary network use.
            listener.remove()
        }
    }


}