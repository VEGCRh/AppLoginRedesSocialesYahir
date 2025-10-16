package com.example.apploginredessocialesyahir.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apploginredessocialesyahir.ui.about.AboutScreen
import com.example.apploginredessocialesyahir.ui.home.HomeScreen
import com.example.apploginredessocialesyahir.ui.login.LoginScreen
import com.example.apploginredessocialesyahir.ui.login.LoginViewModel
import com.example.apploginredessocialesyahir.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val loginState by loginViewModel.loginState.collectAsState()
    val context = LocalContext.current
    
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(AppScreens.LoginScreen.route) {
                        popUpTo(AppScreens.SplashScreen.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                loginState = loginState,
                onLoginClick = { email, password ->
                    loginViewModel.loginWithEmailAndPassword(email, password)
                },
                onTwitterLoginClick = {
                    val activity = context as? Activity
                    if (activity != null) {
                        loginViewModel.loginWithTwitter(activity)
                    } else {
                        loginViewModel.resetState()
                    }
                },
                onErrorDismiss = {
                    loginViewModel.resetState()
                }
            )
            
            // Observar el estado de login y navegar si es exitoso
            when (loginState) {
                is com.example.apploginredessocialesyahir.ui.login.LoginState.Success -> {
                    navController.navigate(AppScreens.HomeScreen.route) {
                        popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                    }
                    loginViewModel.resetState()
                }
                else -> {}
            }
        }
        
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                username = "Usuario",
                onLogout = {
                    navController.navigate(AppScreens.LoginScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onAboutClick = {
                    navController.navigate(AppScreens.AboutScreen.route)
                }
            )
        }
        
        composable(route = AppScreens.AboutScreen.route) {
            AboutScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object LoginScreen : AppScreens("login_screen")
    object HomeScreen : AppScreens("home_screen")
    object AboutScreen : AppScreens("about_screen")
}