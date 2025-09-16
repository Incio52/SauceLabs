# 🚀 Documentación del Proyecto de Automatización - Serenity Sauce

## 📋 Tabla de Contenidos
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Arquitectura y Tecnologías](#arquitectura-y-tecnologías)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración y Setup](#configuración-y-setup)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [Reportes](#reportes)
- [Escenarios de Prueba](#escenarios-de-prueba)
- [Patrones de Diseño](#patrones-de-diseño)
- [Troubleshooting](#troubleshooting)

## 🎯 Descripción del Proyecto

Este proyecto implementa un framework de automatización de pruebas end-to-end para la aplicación **SauceDemo** utilizando las mejores prácticas de BDD (Behavior Driven Development) con Serenity BDD, Cucumber y Allure.

### Objetivos
- ✅ Automatizar flujos críticos de e-commerce
- ✅ Implementar patrón Screenplay para mantenibilidad
- ✅ Generar reportes visuales y detallados
- ✅ Aplicar principios de BDD con Gherkin
- ✅ Manejo robusto de datos de prueba (TDM)

## 🏗️ Arquitectura y Tecnologías

### Stack Tecnológico
| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 11 | Lenguaje de programación |
| **Maven** | 3.8+ | Gestión de dependencias |
| **Serenity BDD** | 3.9.8 | Framework de automatización |
| **Cucumber** | 7.14.0 | BDD testing framework |
| **JUnit 4** | 4.13.2 | Testing framework |
| **WebDriver Manager** | 5.8.0 | Gestión automática de drivers |
| **Allure** | 2.24.0 | Reportes avanzados |
| **PostgreSQL** | 42.7.4 | Base de datos para TDM |
| **SQL Server** | 12.6.1 | Base de datos alternativa |

### Patrón de Arquitectura
```
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                     │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐  │
│  │   Features      │  │  Step Definitions│  │   Runners   │  │
│  │   (Gherkin)     │  │   (Cucumber)    │  │   (JUnit)   │  │
│  └─────────────────┘  └─────────────────┘  └─────────────┘  │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE LÓGICA DE NEGOCIO                │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐  │
│  │     Tasks       │  │   Questions     │  │  Interactions│  │
│  │  (Acciones)     │  │  (Validaciones) │  │  (Elementos) │  │
│  └─────────────────┘  └─────────────────┘  └─────────────┘  │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE INFRAESTRUCTURA                  │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐  │
│  │  User Interfaces│  │     Utils       │  │    Config   │  │
│  │  (Page Objects) │  │  (Helpers)      │  │  (Settings) │  │
│  └─────────────────┘  └─────────────────┘  └─────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Estructura del Proyecto

```
serenity-sauce/
├── 📁 src/
│   ├── 📁 main/java/com/tuempresa/automation/
│   │   ├── 📁 annotations/          # Anotaciones personalizadas
│   │   ├── 📁 config/               # Configuraciones del proyecto
│   │   ├── 📁 drivers/              # Configuración de WebDriver
│   │   ├── 📁 interactions/         # Interacciones con elementos
│   │   ├── 📁 models/               # Modelos de datos
│   │   ├── 📁 questions/            # Validaciones y preguntas
│   │   ├── 📁 tasks/                # Tareas/acciones del usuario
│   │   ├── 📁 userinterfaces/       # Page Objects (UI Elements)
│   │   └── 📁 utils/                # Utilidades y helpers
│   └── 📁 test/
│       ├── 📁 java/com/tuempresa/automation/
│       │   ├── 📁 runners/          # Runners de Cucumber
│       │   └── 📁 stepdefinitions/  # Definiciones de pasos
│       └── 📁 resources/
│           ├── 📁 data/             # Datos de prueba (TDM)
│           └── 📁 features/         # Archivos .feature (Gherkin)
├── 📄 pom.xml                      # Configuración Maven
├── 📄 serenity.properties          # Configuración Serenity
├── 📄 allure.properties            # Configuración Allure
└── 📁 target/                      # Archivos generados
    ├── 📁 serenity/                # Reportes Serenity
    └── 📁 allure-results/          # Resultados Allure
```

## ⚙️ Configuración y Setup

### Prerrequisitos
- **Java 11** o superior
- **Maven 3.8+**
- **Chrome Browser** (para pruebas web)
- **IDE** (IntelliJ IDEA recomendado)

### Instalación
1. **Clonar el repositorio**
   ```bash
   git clone https://bitbucket.org/ibmfic/incast.git
   cd incast
   ```

2. **Instalar dependencias**
   ```bash
   mvn clean install
   ```

3. **Configurar drivers**
   - Los drivers se descargan automáticamente con WebDriver Manager
   - ChromeDriver se encuentra en `drivers/chromedriver-win64/`

### Configuraciones Importantes

#### `serenity.properties`
```properties
# Configuración del navegador
webdriver.driver=chrome
webdriver.chrome.driver=drivers/chromedriver-win64/chromedriver.exe

# Configuración de Serenity
serenity.project.name=Serenity Sauce Demo
serenity.take.screenshots=FOR_EACH_ACTION
serenity.record.failures.only=true

# Configuración de Allure
allure.results.directory=target/allure-results
```

#### `allure.properties`
```properties
# Configuración de Allure
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://example.com/issue/{}
allure.link.tms.pattern=https://example.com/tms/{}
```

## 🧪 Ejecución de Pruebas

### Comandos Básicos
```bash
# Ejecutar todas las pruebas
mvn test

# Limpiar y ejecutar pruebas
mvn clean test

# Ejecutar pruebas específicas
mvn test -Dtest=CucumberTestSuite

# Ejecutar con tags específicos
mvn test -Dcucumber.filter.tags="@login"
```

### Comandos de Reportes
```bash
# Generar reporte de Serenity
mvn serenity:aggregate

# Generar reporte de Allure
mvn allure:report

# Abrir reporte de Serenity
start target/serenity/index.html

# Abrir reporte de Allure
start target/allure-report/index.html
```

### Scripts de Ejecución
- **Windows:** `run-tests.bat`
- **Linux/Mac:** `run-tests.sh`
- **IntelliJ:** `run-tests-intellij.bat`

## 📊 Reportes

### Reporte de Serenity
- **Ubicación:** `target/serenity/index.html`
- **Características:**
  - Dashboard con métricas generales
  - Detalle de cada escenario
  - Screenshots y evidencias
  - Timeline de ejecución
  - Análisis de fallos

### Reporte de Allure
- **Ubicación:** `target/allure-report/index.html`
- **Características:**
  - Vista de árbol de pruebas
  - Métricas y estadísticas
  - Análisis de tendencias
  - Integración con CI/CD

## 🎭 Escenarios de Prueba

### Login
```gherkin
@login @functional @high
Scenario: Login exitoso con credenciales válidas
  Given que estoy en la página de login
  When ingreso las credenciales "standard_user" y "secret_sauce"
  And hago clic en el botón "Login"
  Then debería ser redirigido a la página de inventario
  And debería ver el título "Products"
```

### Carrito de Compras
```gherkin
@cart @functional @high
Scenario: Agregar producto al carrito
  Given que estoy logueado en la aplicación
  When hago clic en "Add to cart" para el producto "Sauce Labs Backpack"
  Then debería ver el contador del carrito con "1"
  And el botón debería cambiar a "Remove"
```

### Checkout
```gherkin
@cart @checkout @functional @high
Scenario: Completar proceso de checkout completo
  Given que agregué el producto "Sauce Labs Backpack" al carrito
  When navego al carrito
  And hago clic en "Checkout"
  Then debería ser redirigido a la página de información de checkout
  When completo el formulario de checkout con "Juan", "Pérez" y "12345"
  And hago clic en "Continue" en la página de checkout
  Then debería ver el botón "Finish" en la página de checkout
  When hago clic en "Finish" en la página de checkout
  Then debería ver la página de confirmación de pedido
```

## 🎨 Patrones de Diseño

### 1. Screenplay Pattern
```java
// Actor realiza acciones
Actor actor = OnStage.theActorInTheSpotlight();
actor.attemptsTo(
    OpenTheApplication.onThePage(),
    LogIn.withCredentials("user", "pass")
);
```

### 2. Page Object Model
```java
// Elementos de la página
public static final Target LOGIN_BUTTON = Target.the("botón login")
    .located(By.id("login-button"));
```

### 3. Builder Pattern
```java
// Construcción de tareas complejas
public class LogIn {
    public static LogIn withCredentials(String username, String password) {
        return new LogIn(username, password);
    }
}
```

### 4. Strategy Pattern
```java
// Diferentes estrategias de validación
public static void validateElement(Actor actor, String elementType) {
    switch (elementType) {
        case "button": validateButton(actor); break;
        case "field": validateField(actor); break;
    }
}
```

## 🔧 Troubleshooting

### Problemas Comunes

#### 1. Driver no encontrado
```bash
# Verificar que ChromeDriver esté en la ruta correcta
ls drivers/chromedriver-win64/chromedriver.exe
```

#### 2. Pruebas fallan por timeout
```properties
# Aumentar timeout en serenity.properties
serenity.timeout=30
```

#### 3. Reportes no se generan
```bash
# Limpiar y regenerar
mvn clean test
mvn serenity:aggregate
```

#### 4. Problemas de compilación
```bash
# Limpiar y recompilar
mvn clean compile
```

### Logs y Debugging
- **Logs de Serenity:** `target/serenity/logs/`
- **Screenshots:** `target/serenity/screenshots/`
- **Logs de Maven:** `target/surefire-reports/`

## 📈 Métricas y KPIs

### Métricas de Calidad
- **Cobertura de Pruebas:** 85.7% (6/7 escenarios)
- **Tiempo de Ejecución:** ~1m 45s
- **Estabilidad:** 85.7% de éxito
- **Mantenibilidad:** Alta (patrón Screenplay)

### Métricas de Rendimiento
- **Prueba más rápida:** 10s 187ms
- **Prueba más lenta:** 20s 541ms
- **Tiempo promedio:** ~15s por escenario

## 🚀 Próximos Pasos

### Mejoras Planificadas
- [ ] Implementar pruebas de API
- [ ] Agregar pruebas de rendimiento
- [ ] Integración con CI/CD
- [ ] Pruebas en múltiples navegadores
- [ ] Implementar paralelización

### Expansión del Framework
- [ ] Agregar más escenarios de negocio
- [ ] Implementar pruebas de regresión
- [ ] Agregar pruebas de accesibilidad
- [ ] Implementar pruebas de seguridad

## 📞 Soporte y Contacto

Para soporte técnico o consultas sobre el proyecto:
- **Documentación:** Ver este archivo
- **Issues:** Crear issue en el repositorio
- **Logs:** Revisar `target/serenity/logs/`

---

**Última actualización:** Diciembre 2024
**Versión del proyecto:** 1.0.0
**Mantenido por:** Equipo de Automatización
