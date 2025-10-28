package com.example.authexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TaskViewModel : ViewModel() {
    private var _taskList = MutableStateFlow<List<Task>>(emptyList())
    var taskList = _taskList.asStateFlow()

    var db = Firebase.firestore

    init {
        viewModelScope.launch { getTaskList() }
    }

    suspend fun getTaskList() {
        val snapshot = db.collection("tasks")
            .get()
            .await()

        _taskList.value = snapshot.documents.map { task ->
            Task(
                id = task.id,
                title = task.getString("title") ?: "",
                isComplete = task.getBoolean("isComplete") ?: false
            )
        }
    }

//    override suspend fun addTask(title: String, userId: String): Result<Unit> {
//        return try {
//            val newTask = Task(title = title, userId = userId)
//            tasksCollection.add(newTask.toMap()).await()
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

//    override suspend fun updateTaskStatus(taskId: String, isComplete: Boolean): Result<Unit> {
//        return try {
//            tasksCollection.document(taskId)
//                .update("isComplete", isComplete)
//                .await()
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

//    override fun getRealtimeTasks(userId: String): Flow<List<Task>> = callbackFlow {
//        val query = tasksCollection.whereEqualTo("userId", userId)
//        // Note: Add .orderBy("createdAt") for sorting, requires Firestore index
//
//        // 1. Attach the Snapshot Listener
//        val listener = query.addSnapshotListener { snapshot, error ->
//            if (error != null) {
//                // If there's an error, cancel the flow with the exception
//                cancel(CancellationException("Firestore listener failed", error))
//                return@addSnapshotListener
//            }
//
//            // 2. Map documents to Kotlin objects and send to the Flow
//            val tasks = snapshot?.documents?.map { doc ->
//                Task(
//                    id = doc.id,
//                    userId = doc.getString("userId") ?: "",
//                    title = doc.getString("title") ?: "Untitled",
//                    isComplete = doc.getBoolean("isComplete") ?: false
//                    // createdAt is omitted for brevity in mapping
//                )
//            } ?: emptyList()
//
//            trySend(tasks)
//        }
//
//        // 3. Cleanup: Remove the listener when the flow is closed/cancelled
//        awaitClose { listener.remove() }
//    }

}

data class Task(
    val id: String = "",
    val title: String = "",
    val isComplete: Boolean = false,
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "title" to title,
            "isComplete" to isComplete
        )
    }
}