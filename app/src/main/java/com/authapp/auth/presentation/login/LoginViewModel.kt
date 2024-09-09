package com.authapp.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authapp.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface LoginState{
    data object Idle : LoginState
    data object Success : LoginState
    data class Error(val e : Exception) : LoginState
    data object Loading : LoginState
}

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: AuthRepository): ViewModel(){


    var state : LoginState by mutableStateOf(LoginState.Idle)

    fun login(email : String, password : String){
        viewModelScope.launch {

            state = LoginState.Loading

            repository.login(email, password).onRight { resp ->
                val token = resp.token
                repository.saveJWT(token)
                state = LoginState.Success
            }.onLeft {

                println(it)

                state = LoginState.Error(it)
            }




        }
    }


}