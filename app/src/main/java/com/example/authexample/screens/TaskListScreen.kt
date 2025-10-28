package com.example.authexample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.authexample.TaskViewModel

@Composable
fun TaskListScreen(modifier: Modifier = Modifier, navController: NavController, taskViewModel: TaskViewModel) {
    val tasks = taskViewModel.taskList.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Task List", fontSize = 40.sp)
        tasks.value.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.id
                )
                Text(
                    text = task.title
                )
            }
        }

        Button(onClick = {
            navController.navigate("home")
        }) {
            Text(text = "Home")
        }
    }
}

