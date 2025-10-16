# Configuración de Firebase para Desarrolladores

## Problema: Error de Certificate Hash

Si ves el error **"there was an error while trying to get your package certificate hash"**, es porque necesitas configurar tu propio archivo `google-services.json` con tu certificado SHA-1.

## Solución

### Paso 1: Obtener tu SHA-1

Ejecuta en la terminal del proyecto:

```bash
./gradlew signingReport
```

Busca la línea que dice `SHA1:` en la sección **Variant: debug** y copia el hash (ejemplo: `AA:BB:CC:DD:...`).

### Paso 2: Agregar tu SHA-1 a Firebase

1. Ve a la [Consola de Firebase](https://console.firebase.google.com/)
2. Selecciona el proyecto: **apploginredessocialesyahir**
3. Ve a **Project Settings** (⚙️ en la parte superior izquierda)
4. En la pestaña **Your apps**, selecciona la app Android
5. Desplázate hasta **SHA certificate fingerprints**
6. Haz clic en **Add fingerprint** y pega tu SHA-1
7. Guarda los cambios

### Paso 3: Descargar tu google-services.json

1. En la misma página de configuración de la app
2. Haz clic en **Download google-services.json**
3. Reemplaza el archivo en tu proyecto:
   - Copia el archivo descargado a: `app/google-services.json`
   - También copia una copia en la raíz: `google-services.json`

### Paso 4: Sincronizar y Ejecutar

```bash
./gradlew clean
./gradlew build
```

Ahora deberías poder ejecutar la app sin problemas.

## Nota Importante

- El archivo `google-services.json` está en `.gitignore`, así que **NO se subirá a Git**
- Cada desarrollador debe tener su propia copia local
- Si hay cambios en la configuración de Firebase, comunícalo al equipo
- Usa `google-services.json.example` como referencia de la estructura
