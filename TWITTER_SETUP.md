# Configuración de Autenticación con Twitter/X

## Pasos para configurar Twitter/X Authentication en Firebase

### 1. Crear una App en Twitter Developer Portal

1. Ve a [Twitter Developer Portal](https://developer.twitter.com/en/portal/dashboard)
2. Crea un nuevo proyecto y una nueva app
3. En la configuración de tu app, ve a "User authentication settings"
4. Habilita OAuth 2.0
5. Agrega las siguientes URLs de callback:
   ```
   https://apploginredessocialesyahir.firebaseapp.com/__/auth/handler
   ```
6. Guarda tu **API Key** y **API Secret Key**

### 2. Configurar Twitter en Firebase Console

1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Selecciona tu proyecto: `apploginredessocialesyahir`
3. Ve a **Authentication** > **Sign-in method**
4. Habilita **Twitter**
5. Ingresa tu **API Key** y **API Secret Key** de Twitter
6. Copia la **Callback URL** que Firebase te proporciona
7. Pega esta URL en la configuración de tu app de Twitter (paso 1.5)

### 3. Probar la Autenticación

1. Compila y ejecuta la aplicación
2. Haz clic en "Continuar con Twitter"
3. Inicia sesión con tu cuenta de Twitter
4. La app debería redirigirte a la pantalla de inicio (HomeScreen)

## Notas Importantes

- Asegúrate de que tu app de Twitter esté en modo de producción para que funcione con usuarios reales
- La autenticación con Twitter usa OAuth 2.0 a través de Firebase
- No necesitas configurar callbacks manuales en el código, Firebase lo maneja automáticamente

## Solución de Problemas

### Error: "Invalid OAuth callback URL"
- Verifica que la URL de callback en Twitter coincida exactamente con la de Firebase

### Error: "API Key is invalid"
- Verifica que hayas copiado correctamente la API Key y Secret de Twitter

### Error: "Twitter authentication failed"
- Asegúrate de haber habilitado Twitter en Firebase Console
- Verifica que las credenciales de Twitter estén correctamente configuradas

## Estructura del Proyecto

```
app/
├── src/main/java/com/example/apploginredessocialesyahir/
│   ├── MainActivity.kt
│   ├── navigation/
│   │   └── AppNavigation.kt
│   ├── ui/
│   │   ├── login/
│   │   │   ├── LoginScreen.kt
│   │   │   └── LoginViewModel.kt
│   │   ├── home/
│   │   │   └── HomeScreen.kt
│   │   └── theme/
│   │       └── Color.kt
│   └── utils/
│       └── TwitterAuthHelper.kt
```

## Commit para feature/login

Este código está listo para tu primer commit en la rama `feature/login`:

```bash
git checkout -b feature/login
git add .
git commit -m "feat: Implementar autenticación con Twitter/X

- Agregar TwitterAuthHelper para manejar autenticación con Twitter/X
- Actualizar LoginScreen con botón de Twitter
- Agregar método loginWithTwitter en LoginViewModel
- Configurar permisos de internet en AndroidManifest
- Agregar dependencias de Twitter en build.gradle.kts
- Implementar login con email/password usando Firebase Auth"
```
