# 🚀 Serenity Sauce - Framework de Automatización

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-3.9.8-green.svg)](https://serenity-bdd.github.io/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg)](https://cucumber.io/)

## 📋 Descripción

Framework de automatización de pruebas end-to-end para la aplicación **SauceDemo** utilizando Serenity BDD, Cucumber y Allure. Implementa el patrón Screenplay para una arquitectura mantenible y escalable.

## 🏗️ Arquitectura

- **Patrón Screenplay**: Arquitectura limpia y mantenible
- **BDD con Cucumber**: Escenarios en lenguaje natural (Gherkin)
- **Page Object Model**: Reutilización de elementos UI
- **Test Data Management**: Integración con bases de datos
- **Reportes Avanzados**: Serenity + Allure

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 11+
- Maven 3.8+
- Chrome Browser

### Instalación
```bash
# Clonar el repositorio
git clone https://bitbucket.org/ibmfic/incast.git
cd incast

# Instalar dependencias
mvn clean install
```

### Ejecución
```bash
# Ejecutar todas las pruebas
mvn test

# Generar reportes
mvn serenity:aggregate
mvn allure:report
```

## 📊 Reportes

- **Serenity**: `target/serenity/index.html`
- **Allure**: `target/allure-report/index.html`

## 📁 Estructura del Proyecto

```
src/
├── main/java/
│   └── com/tuempresa/automation/
│       ├── tasks/          # Acciones del usuario
│       ├── questions/      # Validaciones
│       ├── userinterfaces/ # Page Objects
│       └── utils/          # Utilidades
└── test/
    ├── java/
    │   └── stepdefinitions/ # Step Definitions
    └── resources/
        └── features/       # Archivos .feature
```

## 🧪 Escenarios de Prueba

- ✅ Login con credenciales válidas
- ✅ Agregar productos al carrito
- ✅ Proceso completo de checkout
- ✅ Gestión del carrito de compras

## 📈 Métricas

- **Cobertura**: 85.7% (6/7 escenarios)
- **Tiempo de Ejecución**: ~1m 45s
- **Estabilidad**: 85.7% de éxito

## 🔧 Configuración

### Archivos de Configuración
- `serenity.properties` - Configuración de Serenity
- `allure.properties` - Configuración de Allure
- `pom.xml` - Dependencias Maven

### Variables de Entorno
```properties
# Navegador
webdriver.driver=chrome

# Screenshots
serenity.take.screenshots=FOR_EACH_ACTION

# Reportes
allure.results.directory=target/allure-results
```

## 🚀 CI/CD

El proyecto está configurado para integración continua con:
- Maven Surefire Plugin
- Serenity Maven Plugin
- Allure Maven Plugin

## 📚 Documentación

Para documentación completa, ver: [DOCUMENTACION_PROYECTO.md](DOCUMENTACION_PROYECTO.md)

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit los cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📞 Soporte

Para soporte técnico o consultas:
- **Issues**: Crear issue en Bitbucket
- **Documentación**: Ver `DOCUMENTACION_PROYECTO.md`
- **Logs**: Revisar `target/serenity/logs/`

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**Desarrollado por:** Equipo de Automatización  
**Última actualización:** Diciembre 2024  
**Versión:** 1.0.0