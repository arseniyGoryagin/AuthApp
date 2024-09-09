package com.authapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.authapp.auth.presentation.login.Login
import com.authapp.auth.presentation.register.Register
import com.authapp.auth.presentation.welcome.WelcomeScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.authapp.AppViewModel
import com.authapp.AuthState
import com.authapp.auth.presentation.util.components.CenterLoadingCircle
import com.authapp.screens.Home


@Composable
fun BankPickApp(appViewModel: AppViewModel = hiltViewModel()){

    val authState = appViewModel.authState


    if(authState is AuthState.Loading){
        CenterLoadingCircle(modifier = Modifier.fillMaxSize())
    }else {

        val startDestination = if(authState is AuthState.NotAuth){"auth"}else{"Home"}

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = startDestination) {

            // Auth
            navigation(
                startDestination = "welcome",
                route = "auth"
            ) {
                composable(
                    route = "welcome"
                ) {
                    WelcomeScreen(navController)
                }

                composable(
                    route = "login",
                ) {
                    Login(navController)
                }

                composable(
                    route = "register",
                ) {
                    Register(navController)
                }


            }


            composable(
                route = "Home",
            ) {
                Home(navController = navController)
            }

        }
    }
}