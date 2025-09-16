# Instrucciones para Ejecutar las Pruebas en Otra PC

## Problema Común
El error `DriverConfigurationError: Could not instantiate class org.openqa.selenium.chrome.Chrome` indica que la PC no tiene Chrome instalado o ChromeDriver no está disponible.

## Soluciones

### Opción 1: Configuración Automática (Recomendada)

#### En Windows:
```bash
# Ejecutar el script de configuración automática
setup-environment.bat
```

#### En Linux/Mac:
```bash
# Ejecutar el script de configuración automática
chmod +x setup-environment.sh
./setup-environment.sh
```

### Opción 2: Configuración Manual

#### Si tienes Chrome instalado:
1. El proyecto usará WebDriver Manager automáticamente
2. No necesitas instalar ChromeDriver manualmente
3. Ejecuta: `mvn clean test`

#### Si NO tienes Chrome pero tienes Firefox:
1. Copia el archivo de configuración de Firefox:
   ```bash
   copy serenity-firefox.properties serenity.properties
   ```
2. Ejecuta: `mvn clean test`

#### Si no tienes ningún navegador:
1. Instala Chrome desde: https://www.google.com/chrome/
2. O instala Firefox desde: https://www.mozilla.org/firefox/
3. Luego ejecuta el script de configuración automática

### Opción 3: Usar Docker (Para entornos sin navegadores)

Si no puedes instalar navegadores, puedes usar Docker:

```bash
# Crear un Dockerfile
echo "FROM maven:3.8.4-openjdk-11-slim" > Dockerfile
echo "RUN apt-get update && apt-get install -y wget gnupg" >> Dockerfile
echo "RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -" >> Dockerfile
echo "RUN echo 'deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main' >> /etc/apt/sources.list.d/google-chrome.list" >> Dockerfile
echo "RUN apt-get update && apt-get install -y google-chrome-stable" >> Dockerfile
echo "WORKDIR /app" >> Dockerfile
echo "COPY . ." >> Dockerfile
echo "RUN mvn clean compile" >> Dockerfile
echo "CMD [\"mvn\", \"test\"]" >> Dockerfile

# Construir y ejecutar
docker build -t serenity-tests .
docker run --rm -v %cd%/target:/app/target serenity-tests
```

## Verificación de Requisitos

### Verificar Java:
```bash
java -version
# Debe ser Java 11 o superior
```

### Verificar Maven:
```bash
mvn -version
# Debe ser Maven 3.6 o superior
```

### Verificar Navegadores:
```bash
# Windows
where chrome
where firefox

# Linux/Mac
which google-chrome
which firefox
```

## Comandos de Ejecución

### Ejecutar todas las pruebas:
```bash
mvn clean test
```

### Ejecutar pruebas específicas:
```bash
# Solo pruebas de login
mvn clean test -Dcucumber.filter.tags="@login"

# Solo pruebas de carrito
mvn clean test -Dcucumber.filter.tags="@cart"

# Solo pruebas críticas
mvn clean test -Dcucumber.filter.tags="@critical"
```

### Generar reportes:
```bash
# Generar reporte de Serenity
mvn serenity:aggregate

# Generar reporte de Allure
mvn allure:report
```

## Solución de Problemas

### Error: "Could not start a new session"
- **Causa**: Navegador no instalado o ChromeDriver incompatible
- **Solución**: Usar WebDriver Manager o instalar navegador compatible

### Error: "WebDriver was unable to create a new instance"
- **Causa**: Permisos insuficientes o configuración incorrecta
- **Solución**: Ejecutar como administrador o verificar configuración

### Error: "Invalid address of the remote"
- **Causa**: Configuración de Selenium Grid incorrecta
- **Solución**: Verificar que no esté configurado para usar Selenium Grid

## Archivos de Configuración

- `serenity.properties`: Configuración para Chrome (desarrollo local)
- `serenity-ci.properties`: Configuración para CI/CD (headless)
- `serenity-firefox.properties`: Configuración para Firefox (alternativa)

## Notas Importantes

1. **WebDriver Manager**: El proyecto incluye WebDriver Manager que descarga automáticamente los drivers correctos
2. **Compatibilidad**: Las pruebas funcionan con Chrome, Firefox, Edge y Safari
3. **Headless**: Para CI/CD, usa `serenity-ci.properties` que ejecuta en modo headless
4. **Reportes**: Los reportes se generan en `target/serenity` y `target/allure-results`
