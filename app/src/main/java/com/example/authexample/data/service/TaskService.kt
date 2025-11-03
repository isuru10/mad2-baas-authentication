package com.example.authexample.data.service

import com.example.authexample.data.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskService {
    suspend fun addTask(title: String): Result<Unit>
    fun getRealtimeTasks(): Flow<List<Task>>
}