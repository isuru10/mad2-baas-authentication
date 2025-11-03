package com.example.authexample.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authexample.data.models.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private var _taskList = MutableStateFlow<List<Task>>(emptyList())
    var taskList = _taskList.asStateFlow()

    init {
        viewModelScope.launch {
            getTaskList()
        }
    }

    fun getTaskList() {
        db.collection("tasks").document("K8h44DbutQa4KeuWUd7D")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {
                    _taskList.value = listOf(
                        Task(
                            id = value.id,
                            title = value.getString("title") ?: "",
                            isComplete = value.getBoolean("isComplete") ?: false
                        )
                    )
//                    _taskList.value = value.documents.map { task ->
//                        Task(
//                            id = task.id,
//                            title = task.getString("title") ?: "",
//                            isComplete = task.getBoolean("isComplete") ?: false
//                        )
//                    }
                }
            }
    }
}
