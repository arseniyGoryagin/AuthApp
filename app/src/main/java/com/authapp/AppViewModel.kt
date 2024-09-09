package com.authapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authapp.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



sealed interface AuthState{
    object Success : AuthState
    object Loading : AuthState
    object NotAuth : AuthState
}

@HiltViewModel
class AppViewModel @Inject constructor(val repository: AuthRepository) : ViewModel(){

    var authState : AuthState by mutableStateOf(AuthState.Loading)

    init {
        viewModelScope.launch {
            try {
                authState = AuthState.Loading
                val res = repository.authenticate()

                if(!res.isSuccessful){
                    authState = AuthState.NotAuth
                }else{
                    authState = AuthState.Success
                }
            }catch (e : retrofit2.HttpException){
                authState = AuthState.NotAuth
            }catch (e : Exception){
                authState = AuthState.NotAuth
            }
        }
    }

}
