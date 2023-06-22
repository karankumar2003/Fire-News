package com.example.firenews.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.firenews.screens.NewsScreens

@Composable
fun LogInScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Icon(Icons.Default.Lock,"null",modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text("Sign In", modifier = Modifier.padding(4.dp), fontSize = 25.sp)

        val context = LocalContext.current
        UserForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            buttonText = "Sign In",
            onButtonClick = { email, password ->
                authViewModel.signInWithEmailAndPassword(email,password){
                    navController.navigate(NewsScreens.MainScreen.name)
                }
            },
            newUserText = "Don't have an account? ",
            signUpText = "Sign Up",
            signUpTextOnClick = {
                navController.navigate(NewsScreens.CreateAccountScreen.name)
            }
        )
    }
}