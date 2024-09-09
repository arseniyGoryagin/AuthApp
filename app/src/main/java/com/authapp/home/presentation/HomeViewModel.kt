package com.authapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authapp.auth.domain.model.User
import com.authapp.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface HomeState{
    object Loading : HomeState
    data class Error(val e : Exception) : HomeState
    data class Success(val userData : User) : HomeState
}


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    var homeState : HomeState by mutableStateOf(HomeState.Loading)


    init {
        viewModelScope.launch {
                repository.getUserData().onRight {
                    homeState = HomeState.Success(it.userData)
                }.onLeft {
                    homeState = HomeState.Error(it)
                }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            repository.logOut()
        }
    }

}