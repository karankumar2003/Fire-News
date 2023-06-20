package com.example.firenews.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.firenews.R
import com.example.firenews.screens.NewsScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Lottie(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)
        )
    }


    LaunchedEffect(key1 = true) {
        delay(2500L)

        if (FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(NewsScreens.MainScreen.name) {
                popUpTo(NewsScreens.SplashScreen.name) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(NewsScreens.LogInScreen.name) {
                popUpTo(NewsScreens.SplashScreen.name) {
                    inclusive = true
                }
            }
        }

    }

}

@Composable
fun Lottie(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    LottieAnimation(
        composition = composition,
        iterations = 1,
        modifier = modifier,
        speed = 2f
    )
}