package com.authapp.auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.authapp.auth.presentation.util.components.CenterLoadingCircle
import com.authapp.auth.presentation.util.components.TextInput
import com.authapp.ui.theme.BackGrey
import com.com.auth.R



@Composable
fun Register(navController: NavController, registerViewModel: RegisterViewModel = hiltViewModel()) {

    val onSignUp = { email : String, password : String, name : String, phone : String ->
        registerViewModel.register(email, password, phone, name)
    }

    val onLogin = {
        navController.navigate("login"){
            popUpTo("welcome")
        }
    }

    val onBackClick = {
        navController.navigate("welcome"){
            popUpTo("auth")
        }
    }


    val state = registerViewModel.state


    RegisterContent(
        registerState = state,
        onSignUp = onSignUp,
        onBackClick = onBackClick,
        onLogin = onLogin)


}


@Composable
fun RegisterContent(
    registerState : RegisterState,
    onSignUp : ( email : String, password : String, name : String, phone : String) -> Unit,
    onBackClick : () -> Unit,
    onLogin : () -> Unit
){


    var emailString = remember {
        mutableStateOf("")
    }

    var passwordString = remember {
        mutableStateOf("")
    }

    var phoneString = remember {
        mutableStateOf("")
    }

    var nameString = remember {
        mutableStateOf("")
    }

    var showPassword = remember {
        mutableStateOf(true)
    }


    val enabled = if(registerState is RegisterState.Loading){false}else{true}
    val error =  if(registerState is RegisterState.Error){true}else{false}
    val showBottomSheet = if(registerState is RegisterState.Success){true}else{false}

    val scrollState = rememberScrollState()


    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 54.dp)
            .verticalScroll(scrollState)
    ){

        IconButton(
            modifier = Modifier
                .height(42.dp)
                .width(42.dp),
            onClick = onBackClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = BackGrey
            )) {
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(53.dp))

        Text(text = "Sign Up    ",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(38.dp))


        TextInput(
            error =   error,
            enabled = enabled,
            name = "Full Name",
            value = nameString.value,
            onValueChange =  { newValue ->
                nameString.value = newValue
            },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth())


        Spacer(modifier = Modifier.height(21.dp))




        TextInput(
            error = error,
            enabled = enabled,
            name = "Phone Number",
            value = phoneString.value,
            onValueChange =  { newValue ->
                phoneString.value = newValue
            },
            leadingIcon = { Icon(Icons.Outlined.Phone, contentDescription = null) },
            modifier = Modifier.fillMaxWidth())


        Spacer(modifier = Modifier.height(21.dp))


        TextInput(
            error = error,
            enabled = enabled,
            name = "Email Address",
            value = emailString.value,
            onValueChange =  { newValue ->
                emailString.value = newValue
            },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth())


        Spacer(modifier = Modifier.height(21.dp))


        TextInput(
            error = error,
            enabled = enabled,
            showText = showPassword.value,
            name = "Password",
            value = passwordString.value,
            onValueChange =  { newValue ->
                passwordString.value= newValue
            },
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = null)

            },
            trailingIcon = { IconButton(onClick = { showPassword.value = !showPassword.value}) {

                val icon = if(showPassword.value){
                    R.drawable.eye_off_outline}else{
                    R.drawable.eye_outline}

                Icon(painter = painterResource(id = icon), contentDescription = null)
            }
            },
            modifier = Modifier.fillMaxWidth())




        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {onSignUp( emailString.value,passwordString.value, nameString.value, phoneString.value)},
            modifier = Modifier.fillMaxWidth()) {


            if(registerState is RegisterState.Loading){
                CenterLoadingCircle(modifier = Modifier.fillMaxSize())
            }else {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(29.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account. Login",
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable { onLogin() }
            )
        }

        if(showBottomSheet){
            SuccessBottomSheet(onLogin)
        }

    }

}