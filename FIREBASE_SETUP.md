# Configuración de Firebase para Desarrolladores

## Problema: Error de Certificate Hash

Si ves el error **"there was an error while trying to get your package certificate hash"**, es porque tu certificado SHA-1 no está registrado en Firebase.

## Solución para Nuevos Desarrolladores

### Paso 1: Obtener tu SHA-1

Ejecuta en la terminal del proyecto:

```bash
./gradlew signingReport
```

Busca la línea que dice `SHA1:` en la sección **Variant: debug** y copia el hash (ejemplo: `AA:BB:CC:DD:...`).

### Paso 2: Enviar tu SHA-1 al Administrador del Proyecto

Envía tu SHA-1 al compañero que administra el proyecto de Firebase para que lo agregue.

---

## Para el Administrador del Proyecto

### Agregar SHA-1 de un Nuevo Desarrollador

1. Ve a la [Consola de Firebase](https://console.firebase.google.com/)
2. Selecciona el proyecto: **apploginredessocialesyahir**
3. Ve a **Project Settings** (⚙️ en la parte superior izquierda)
4. En la pestaña **Your apps**, selecciona la app Android
5. Desplázate hasta **SHA certificate fingerprints**
6. Haz clic en **Add fingerprint** y pega el SHA-1 del nuevo desarrollador
7. Guarda los cambios

### Actualizar google-services.json

1. En la misma página de configuración de la app
2. Haz clic en **Download google-services.json**
3. Reemplaza los archivos en el proyecto:
   - `app/google-services.json`
   - `google-services.json` (raíz)
4. Haz commit y push de los cambios

### Notificar al Equipo

Avisa a todos que hagan pull para obtener el archivo actualizado:

```bash
git pull
./gradlew clean build
```

---

## Notas Importantes

- El archivo `google-services.json` **SÍ está versionado en Git**
- Puede contener múltiples SHA-1 (uno por cada desarrollador)
- Cuando se agregue un nuevo desarrollador, el administrador debe actualizar el archivo
- Todos los desarrolladores deben hacer pull después de cada actualización
