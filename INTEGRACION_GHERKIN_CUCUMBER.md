# 🔗 Integración Gherkin → Cucumber → JUnit → Maven

## 📋 Flujo de Integración

Este documento explica cómo se integran las diferentes tecnologías en el proyecto de automatización:

```
📝 Gherkin (.feature) → 🥒 Cucumber → 🧪 JUnit → 🔧 Maven → 📊 Serenity Reports
```

---

## 1️⃣ **Gherkin** - Especificaciones en Lenguaje Natural

### 📁 Ubicación: `src/test/resources/features/`

```gherkin
# Archivo: cart/cart.feature
@cart @shopping @ecommerce
Feature: Gestión del Carrito de Compras - SauceDemo

  @cart @add_product @smoke @critical
  Scenario: Agregar un producto al carrito
    Given que estoy logueado como "standard_user" con contraseña "secret_sauce"
    And estoy en la página del inventario de productos
    When agrego el producto "Sauce Labs Backpack" al carrito
    Then el carrito debería mostrar 1 producto
```

### 🎯 Características:
- **Lenguaje natural** comprensible por stakeholders
- **Tags** para categorización (`@cart`, `@smoke`, `@critical`)
- **Escenarios reutilizables** con diferentes datos
- **Estructura BDD** (Given-When-Then)

---

## 2️⃣ **Cucumber** - Motor de Ejecución BDD

### 📁 Ubicación: `src/test/java/.../stepdefinitions/`

```java
// Archivo: CartStepDefinitions.java
@Given("que estoy logueado como {string} con contraseña {string}")
public void queEstoyLogueadoComoContrasena(String usuario, String contrasena) {
    AllureUtils.step("Abrir la aplicación SauceDemo", () -> {
        ExplicitHelpers.openApplicationAndWait(actor, TestConstants.SAUCE_DEMO_URL, 5);
    });
    
    AllureUtils.step("Realizar login con credenciales: " + usuario, () -> {
        actor.attemptsTo(LogIn.withCredentials(usuario, contrasena));
    });
}

@When("agrego el producto {string} al carrito")
public void agregoElProductoAlCarrito(String producto) {
    AllureUtils.step("Agregar producto al carrito: " + producto, () -> {
        actor.attemptsTo(AddProductToCart.named(producto));
    });
}
```

### 🔧 Configuración en `pom.xml`:
```xml
<dependency>
    <groupId>net.serenity-bdd</groupId>
    <artifactId>serenity-cucumber</artifactId>
    <version>${serenity.cucumber.version}</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
</dependency>
```

---

## 3️⃣ **JUnit** - Framework de Testing

### 📁 Ubicación: `src/test/java/.../runners/`

```java
// Archivo: CucumberTestSuite.java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.tuempresa.automation.stepdefinitions",
    plugin = {"pretty"},
    tags = "@cart"
)
public class CucumberTestSuite {}
```

### 🎯 Función de JUnit:
- **Runner principal** que ejecuta Cucumber
- **Integración con Serenity** (`CucumberWithSerenity`)
- **Configuración de features** y step definitions
- **Filtrado por tags** (`@cart`, `@smoke`, etc.)

### 🔧 Configuración en `pom.xml`:
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>${junit.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>${cucumber.version}</version>
</dependency>
```

---

## 4️⃣ **Maven** - Gestión del Proyecto

### 🔧 Plugin Surefire (Ejecutor de Pruebas):
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
    <configuration>
        <includes>
            <include>**/*TestSuite.java</include>
        </includes>
        <testFailureIgnore>true</testFailureIgnore>
        <systemPropertyVariables>
            <cucumber.filter.tags>${cucumber.filter.tags}</cucumber.filter.tags>
        </systemPropertyVariables>
    </configuration>
</plugin>
```

### 📊 Plugin Serenity (Generador de Reportes):
```xml
<plugin>
    <groupId>net.serenity-bdd.maven.plugins</groupId>
    <artifactId>serenity-maven-plugin</artifactId>
    <version>${serenity.version}</version>
    <executions>
        <execution>
            <id>serenity-reports</id>
            <phase>verify</phase>
            <goals>
                <goal>aggregate</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

---

## 🚀 **Comandos de Ejecución**

### **Opción 1: Comando Completo (Recomendado)**
```bash
mvn clean test serenity:aggregate
```
- ✅ Limpia proyecto
- ✅ Ejecuta todas las pruebas
- ✅ Genera reporte automáticamente
- ✅ Abre `target/serenity/index.html`

### **Opción 2: Paso a Paso**
```bash
# 1. Ejecutar pruebas
mvn clean test

# 2. Generar reporte
mvn serenity:aggregate
```

### **Opción 3: Desde IDE**
```bash
# 1. Run → CucumberTestSuite.java
# 2. Ejecutar en terminal:
mvn serenity:aggregate
```

### **Opción 4: Con Filtros**
```bash
# Solo pruebas críticas
mvn test -Dcucumber.filter.tags="@critical" serenity:aggregate

# Solo pruebas de carrito
mvn test -Dcucumber.filter.tags="@cart" serenity:aggregate

# Solo smoke tests
mvn test -Dcucumber.filter.tags="@smoke" serenity:aggregate
```

---

## 🔄 **Flujo Completo de Ejecución**

### **1. Gherkin → Cucumber**
```
📝 cart.feature
    ↓ (Cucumber lee el archivo)
🥒 CartStepDefinitions.java
    ↓ (Mapea steps a código Java)
🎭 Screenplay Actions (LogIn, AddProductToCart, etc.)
```

### **2. Cucumber → JUnit**
```
🥒 Cucumber ejecuta scenarios
    ↓ (JUnit Runner orquesta)
🧪 CucumberTestSuite.java
    ↓ (Serenity captura resultados)
📊 Serenity Reports
```

### **3. JUnit → Maven**
```
🧪 JUnit ejecuta @Test methods
    ↓ (Surefire Plugin ejecuta)
🔧 Maven Surefire Plugin
    ↓ (Genera resultados)
📁 target/surefire-reports/
```

### **4. Maven → Serenity Reports**
```
🔧 Maven ejecuta serenity:aggregate
    ↓ (Lee resultados de Surefire)
📊 Serenity Plugin procesa datos
    ↓ (Genera HTML reports)
🌐 target/serenity/index.html
```

---

## 📊 **Tipos de Reportes Generados**

### **🎯 Serenity Reports:**
- **index.html** → Reporte principal con dashboard
- **Screenshots** → Capturas automáticas de cada step
- **Logs detallados** → Información de cada acción
- **Métricas** → Tiempo, éxito/fallo, tendencias

### **📋 Surefire Reports:**
- **XML** → Resultados para CI/CD
- **TXT** → Logs de consola
- **Dump files** → Para debugging

### **🔍 Allure Reports (Opcional):**
```bash
mvn allure:report
mvn allure:serve
```

---

## ⚙️ **Configuración Clave**

### **📄 serenity.properties:**
```properties
# Driver configuration
webdriver.driver=chrome
webdriver.chrome.driver=drivers/chromedriver.exe

# Report configuration
serenity.take.screenshots=FOR_EACH_ACTION
serenity.outputDirectory=target/serenity
serenity.adapters=allure
```

### **🏷️ Tags en Cucumber:**
```java
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.tuempresa.automation.stepdefinitions",
    tags = "@cart"  // Ejecuta solo pruebas con @cart
)
```

---

## 🎯 **Ventajas de esta Integración**

### **👥 Para el Equipo:**
- **Product Owners** → Leen y validan scenarios en Gherkin
- **QA Engineers** → Escriben step definitions en Java
- **Developers** → Implementan page objects y actions
- **Managers** → Ven reportes visuales con métricas

### **🔧 Para el Proyecto:**
- **Mantenibilidad** → Separación clara de responsabilidades
- **Reutilización** → Steps y actions reutilizables
- **Escalabilidad** → Fácil agregar nuevos scenarios
- **Reportes** → Información detallada y visual

### **🚀 Para CI/CD:**
- **Integración Maven** → Compatible con Jenkins, GitLab, etc.
- **Reportes automáticos** → Generados en cada build
- **Filtrado flexible** → Ejecutar solo pruebas específicas
- **Resultados XML** → Para integración con herramientas

---

## 🛠️ **Troubleshooting**

### **❌ Si no se genera index.html:**
```bash
# Asegurar que serenity:aggregate se ejecute
mvn test serenity:aggregate
```

### **❌ Si fallan las pruebas:**
```bash
# Ver logs detallados
mvn test -X

# Ver reportes de fallos
cat target/surefire-reports/*.txt
```

### **❌ Si ChromeDriver no funciona:**
```bash
# Verificar configuración
cat serenity.properties | grep chrome
dir drivers/chromedriver.exe
```

---

## 📚 **Referencias**

- **Serenity BDD:** https://serenity-bdd.github.io/
- **Cucumber:** https://cucumber.io/docs/cucumber/
- **Gherkin:** https://cucumber.io/docs/gherkin/
- **Maven Surefire:** https://maven.apache.org/surefire/
- **Screenplay Pattern:** https://serenity-bdd.github.io/docs/screenplay/

---

*📝 Documento generado para explicar la integración completa del stack de automatización*
