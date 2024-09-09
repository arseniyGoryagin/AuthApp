package com.authapp.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authapp.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface RegisterState{
    data object Idle : RegisterState
    data object Success : RegisterState
    data class Error(val e : Exception) : RegisterState
    data object Loading : RegisterState
}

@HiltViewModel
class RegisterViewModel @Inject constructor(val repository: AuthRepository)  : ViewModel(){

    var state : RegisterState by mutableStateOf(RegisterState.Idle)

    fun register(email : String, password : String, phone : String, name : String){

        viewModelScope.launch {
            try {
                state = RegisterState.Loading

                val res = repository.register(email, password, phone, name)

                if(!res.isSuccessful){
                    val errorString  = res.body().toString()
                    state = RegisterState.Error(Exception(errorString))
                }else{
                    state = RegisterState.Success
                }

            }catch (e : retrofit2.HttpException){
                state = RegisterState.Success
            }
        }


        }

}