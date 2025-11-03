package com.example.authexample.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.authexample.data.models.AuthState
import com.example.authexample.ui.auth.AuthViewModel
import com.example.authexample.ui.navigation.AppRoutes

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    val authState by authViewModel.authState.collectAsStateWithLifecycle()

    LaunchedEffect(authState) {
        TODO("Use the current state to navigate to appropriate screen")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome Home!", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            navController.navigate("taskList")
        }) {
            Text(text = "Task List")
        }

        Button(onClick = {
            authViewModel.handleLogout()
        }) {
            Text(text = "Logout")
        }
    }
}