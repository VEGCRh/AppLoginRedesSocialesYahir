package com.example.apploginredessocialesyahir.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState
    
    fun loginWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading
                
                if (email.isBlank() || password.isBlank()) {
                    _loginState.value = LoginState.Error("El correo y la contraseña son obligatorios")
                    return@launch
                }
                
                auth.signInWithEmailAndPassword(email, password).await()
                _loginState.value = LoginState.Success
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Error al iniciar sesión")
            }
        }
    }
    
    fun loginWithTwitter(activity: Activity) {
        _loginState.value = LoginState.Loading
        
        val twitterAuthHelper = com.example.apploginredessocialesyahir.utils.TwitterAuthHelper(activity)
        twitterAuthHelper.login { success, errorMessage ->
            if (success) {
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error(errorMessage ?: "Error al iniciar sesión con Twitter")
            }
        }
    }
    
    fun resetState() {
        _loginState.value = LoginState.Initial
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}