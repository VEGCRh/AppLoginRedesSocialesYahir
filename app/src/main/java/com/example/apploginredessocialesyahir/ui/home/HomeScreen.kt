package com.example.apploginredessocialesyahir.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(username: String, onLogout: () -> Unit = {}, onAboutClick: () -> Unit = {}) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    // Fondo con degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFBBDEFB), Color(0xFF90CAF9))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mensaje de bienvenida
            Text(
                text = "¡Bienvenido, ${currentUser?.displayName ?: currentUser?.email ?: "Usuario"}!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Has iniciado sesión correctamente",
                fontSize = 18.sp,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Menú de botones
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuButton("Acerca de", Color(0xFF1976D2), onClick = onAboutClick)
                MenuButton("Cerrar Sesión", Color(0xFFE53935), onClick = {
                    auth.signOut()
                    onLogout()
                })
            }
        }
    }
}

// Composable para botones tipo menú
@Composable
fun MenuButton(title: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}
