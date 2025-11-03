package com.example.authexample.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authexample.data.models.Task
import com.example.authexample.data.service.FireStoreTaskServiceImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TaskViewModel : ViewModel() {

    private val taskService = FireStoreTaskServiceImpl()

    val tasksState: StateFlow<List<Task>> = taskService
        .getRealtimeTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
