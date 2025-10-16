package com.example.apploginredessocialesyahir.utils

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

/**
 * Clase auxiliar para manejar la autenticación con Twitter/X
 */
class TwitterAuthHelper(private val activity: Activity) {
    
    private val auth = FirebaseAuth.getInstance()
    
    companion object {
        private const val TAG = "TwitterAuthHelper"
        const val TWITTER_PROVIDER_ID = "twitter.com"
    }
    
    /**
     * Inicia el proceso de autenticación con Twitter
     */
    fun login(callback: (Boolean, String?) -> Unit) {
        Log.d(TAG, "=== Iniciando login con Twitter ===")
        
        val provider = OAuthProvider.newBuilder(TWITTER_PROVIDER_ID).build()
        
        Log.d(TAG, "Llamando a startActivityForSignInWithProvider...")
        
        auth.startActivityForSignInWithProvider(activity, provider)
            .addOnSuccessListener { authResult ->
                val user = authResult.user
                Log.d(TAG, "✅ LOGIN EXITOSO")
                Log.d(TAG, "Usuario: ${user?.displayName}")
                Log.d(TAG, "Email: ${user?.email}")
                Log.d(TAG, "UID: ${user?.uid}")
                callback(true, null)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "❌ LOGIN FALLIDO: ${exception.message}", exception)
                callback(false, exception.message ?: "Error al iniciar sesión con Twitter")
            }
    }
    
    /**
     * Obtiene el usuario actual de Twitter si está autenticado
     */
    fun getCurrentUser() = auth.currentUser
    
    /**
     * Cierra la sesión de Twitter
     */
    fun logout() {
        auth.signOut()
    }
}
