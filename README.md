# 🚀 Serenity Sauce - Framework de Automatización  
[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)  
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)  
[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-3.9.8-green.svg)](https://serenity-bdd.github.io/)  
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg)](https://cucumber.io/)  

---
## 📋 Indicaciones 
Crear una carpte drivers, dentro de ella poner un chromedriver.exe, ya que va utilizar la version de navegador 

## 📋 Descripción

Framework de automatización de pruebas end-to-end para la aplicación **SauceDemo**, utilizando:

- **Serenity BDD**
- **Cucumber**
- **Allure**

Implementa el **patrón Screenplay**, permitiendo una arquitectura mantenible, escalable y centrada en el comportamiento del usuario.

---

## 🏗️ Arquitectura

- 🧠 **Patrón Screenplay**: Modelo de actores, tareas, preguntas y habilidades  
- ✍️ **BDD con Cucumber**: Escenarios legibles en lenguaje Gherkin  
- 🧱 **Page Object Model**: Separación clara de los elementos UI  
- 🗄️ **Gestión de Datos de Prueba**: Soporte para bases de datos externas  
- 📊 **Reportes Avanzados**: Integración con Serenity y Allure  

---

## 🚀 Inicio Rápido

### 🔧 Prerrequisitos

- ☕ Java 11+
- 📦 Maven 3.8+
- 🌐 Navegador Google Chrome

### ⚙️ Instalación

```bash
# Clonar el repositorio
git clone https://bitbucket.org/ibmfic/incast.git
cd incast

# Instalar dependencias y compilar el proyecto
mvn clean install
▶️ Ejecución de Pruebas
# Ejecutar todas las pruebas
mvn test

# Generar reportes
mvn serenity:aggregate
mvn allure:report
📊 Reportes

📁 Serenity Report: target/serenity/index.html

📁 Allure Report: target/allure-report/index.html


📁 Estructura del Proyecto
src/
├── main/java/
│   └── com/tuempresa/automation/
│       ├── tasks/            # Acciones del usuario
│       ├── questions/        # Validaciones y afirmaciones
│       ├── userinterfaces/   # Objetos de página (Page Objects)
│       └── utils/            # Utilidades comunes
└── test/
    ├── java/
    │   └── stepdefinitions/  # Definiciones de pasos Cucumber
    └── resources/
        └── features/         # Archivos .feature (Gherkin)
🧪 Escenarios de Prueba Implementados

✅ Login con credenciales válidas

✅ Agregar productos al carrito

✅ Proceso completo de checkout

✅ Gestión del carrito de compras

📈 Métricas del Proyecto

📌 Cobertura de pruebas: 85.7% (6 de 7 escenarios completados)

⏱️ Tiempo total de ejecución: ~1 minuto 45 segundos

✅ Estabilidad general: 85.7% de éxito

🔧 Configuración
Archivos de Configuración

serenity.properties → Configuración de Serenity

allure.properties → Configuración de Allure

pom.xml → Dependencias y configuración de build con Maven

Variables de Entorno
# Configuración del navegador
webdriver.driver=chrome

# Captura de pantallas
serenity.take.screenshots=FOR_EACH_ACTION

# Directorio de resultados Allure
allure.results.directory=target/allure-results
🔄 CI/CD

Este proyecto está configurado para integrarse fácilmente con pipelines de CI/CD utilizando:

Maven Surefire Plugin

Serenity Maven Plugin

Allure Maven Plugin

📚 Documentación

Puedes consultar la documentación técnica completa en el archivo:
📄 DOCUMENTACION_PROYECTO.md

🤝 Contribución

¡Contribuciones son bienvenidas! Sigue estos pasos:
git checkout -b feature/nueva-funcionalidad
git commit -m 'Agregar nueva funcionalidad'
git push origin feature/nueva-funcionalidad


