package com.authapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.authapp.auth.presentation.util.components.CenterLoadingCircle
import com.authapp.home.presentation.HomeState
import com.authapp.home.presentation.HomeViewModel


@Composable
fun Home(navController: NavController, homeViewmodel : HomeViewModel = hiltViewModel()){

    val homeState = homeViewmodel.homeState

    HomeContent(
        state = homeState,
        onLogout = {

            homeViewmodel.logOut()

            navController.navigate("auth"){
                popUpTo("welcome")
            }

        }
    )
}


@Composable
fun HomeContent(
    state : HomeState,
    onLogout : () -> Unit
){


    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = "Welcome!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold)


        Spacer(modifier = Modifier.height(29.dp))

        when(state){
            is HomeState.Error -> Text(text = "Error loading user data")
            HomeState.Loading -> CenterLoadingCircle()
            is HomeState.Success -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(text = "name : ${state.userData.name}",
                        fontSize = 16.sp)
                    Text(text = "phone number : ${state.userData.phone}",
                            fontSize = 16.sp)
                    Text(text = "email : ${state.userData.email}",
                        fontSize = 16.sp)
                }

            }
        }


        Spacer(modifier = Modifier.height(29.dp))

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Log out",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }


    }
}