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

    val tasksState: StateFlow<List<Task>> = TODO("Expose the tasks state to the UI for observation")
}
