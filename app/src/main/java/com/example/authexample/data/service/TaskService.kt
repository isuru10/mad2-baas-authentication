package com.example.authexample.data.service

interface TaskService {
    suspend fun addTask(title: String): Result<Unit>
}