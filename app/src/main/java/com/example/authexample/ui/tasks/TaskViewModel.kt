package com.example.authexample.ui.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authexample.data.models.Task
import com.example.authexample.ui.login.AuthState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private var _taskList = MutableStateFlow<List<Task>>(emptyList())
    var taskList = _taskList.asStateFlow()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    var db = Firebase.firestore

    init {
        viewModelScope.launch {
            getTaskList()
        }
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun getTaskList() {
        db.collection("tasks").document("K8h44DbutQa4KeuWUd7D")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {
                    checkAuthStatus()
                    Log.i("AuthStatus", _authState.value?.toString() ?: "")
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
