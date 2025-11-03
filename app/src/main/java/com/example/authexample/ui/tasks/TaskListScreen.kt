package com.example.authexample.ui.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.authexample.ui.navigation.AppRoutes

@Composable
fun TaskListScreen(modifier: Modifier = Modifier, navController: NavController, taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.taskList.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Task List", fontSize = 40.sp)
        tasks.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp),
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
            navController.navigate(AppRoutes.Home.route)
        }) {
            Text(text = "Home")
        }
    }
}

