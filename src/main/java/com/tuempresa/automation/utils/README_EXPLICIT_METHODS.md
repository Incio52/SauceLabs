# Métodos Explícitos - Documentación

Este documento describe los métodos explícitos implementados en el proyecto de automatización con Serenity BDD.

## Estructura de Métodos Explícitos

### 1. ExplicitMethods.java
Contiene métodos explícitos para operaciones comunes de la aplicación.

#### Métodos principales:
- `openApplication(Actor, String)` - Abre la aplicación y espera a que cargue
- `performLogin(Actor, String, String)` - Realiza login con credenciales
- `addProductToCart(Actor, String)` - Agrega un producto al carrito
- `navigateToCart(Actor)` - Navega al carrito
- `verifyPageTitle(Actor, String)` - Verifica el título de la página
- `verifyCartCount(Actor, int)` - Verifica el contador del carrito
- `verifyProductInCart(Actor, String)` - Verifica que un producto está en el carrito

### 2. ExplicitValidations.java
Contiene validaciones explícitas para los tests.

#### Validaciones principales:
- `validateUserIsOnInventoryPage(Actor)` - Valida que el usuario está en inventario
- `validateUserIsOnLoginPage(Actor)` - Valida que el usuario está en login
- `validateCartCount(Actor, int)` - Valida el contador del carrito
- `validateProductIsInCart(Actor, String)` - Valida que un producto está en el carrito
- `validateErrorMessage(Actor, String)` - Valida mensajes de error
- `validateLoginSuccessful(Actor)` - Valida que el login fue exitoso

### 3. ExplicitHelpers.java
Contiene helpers para operaciones comunes.

#### Helpers principales:
- `openApplicationAndWait(Actor)` - Abre la aplicación y espera
- `loginWithStandardUser(Actor)` - Login con usuario estándar
- `addSpecificProductToCart(Actor, String)` - Agrega producto específico
- `addMultipleProductsToCart(Actor, String...)` - Agrega múltiples productos
- `navigateToCart(Actor)` - Navega al carrito
- `isElementVisible(Actor, Target)` - Verifica si un elemento es visible
- `getElementText(Actor, Target)` - Obtiene el texto de un elemento

### 4. TestConstants.java
Contiene constantes utilizadas en los tests.

#### Constantes principales:
- URLs de la aplicación
- Credenciales de usuario
- Nombres de productos
- Títulos de páginas
- Mensajes de error
- Timeouts
- Tags de Cucumber

### 5. ExplicitConfig.java
Configuración para métodos explícitos.

#### Configuraciones principales:
- Timeouts de elementos
- Configuración de esperas
- Configuración de capturas de pantalla
- Configuración de reportes
- Configuración de validaciones

## Cómo usar los Métodos Explícitos

### En Step Definitions:

```java
@Dado("que estoy logueado como {string} con contraseña {string}")
public void queEstoyLogueadoComoContrasena(String user, String pass){
    Actor actor = OnStage.theActorInTheSpotlight();
    ExplicitHelpers.openApplicationAndWait(actor);
    ExplicitHelpers.loginWithCredentials(actor, user, pass);
    ExplicitValidations.validateLoginSuccessful(actor);
}
```

### En Tasks:

```java
public static Performable named(String productName){
    return Task.where("{0} agrega el producto " + productName + " al carrito",
        ExplicitHelpers.waitForElementToBeClickable(actor, target, timeout),
        Click.on(InventoryPage.ADD_TO_CART_BY_NAME.of(productName))
    );
}
```

### En Questions:

```java
public static Question<Integer> value() {
    return actor -> {
        String countText = ExplicitHelpers.getElementText(actor, InventoryPage.CART_BADGE);
        return Integer.parseInt(countText);
    };
}
```

## Ventajas de los Métodos Explícitos

1. **Reutilización**: Los métodos pueden ser reutilizados en múltiples step definitions
2. **Mantenibilidad**: Cambios en la lógica se hacen en un solo lugar
3. **Legibilidad**: El código es más fácil de leer y entender
4. **Consistencia**: Comportamiento consistente en todos los tests
5. **Debugging**: Más fácil identificar problemas específicos
6. **Testing**: Los métodos pueden ser probados independientemente

## Mejores Prácticas

1. **Nombres descriptivos**: Usar nombres que describan claramente la acción
2. **Parámetros claros**: Usar parámetros que sean fáciles de entender
3. **Validaciones explícitas**: Incluir validaciones en los métodos
4. **Manejo de errores**: Incluir manejo de errores apropiado
5. **Documentación**: Documentar el propósito de cada método
6. **Constantes**: Usar constantes para valores que se repiten

## Ejemplos de Uso

### Ejemplo 1: Login con validación
```java
@Dado("que me logueo exitosamente")
public void queMeLogueoExitosamente() {
    Actor actor = OnStage.theActorInTheSpotlight();
    ExplicitHelpers.openApplicationAndWait(actor);
    ExplicitHelpers.loginWithStandardUser(actor);
    ExplicitValidations.validateLoginSuccessful(actor);
}
```

### Ejemplo 2: Agregar producto con validación
```java
@Cuando("agrego {string} al carrito")
public void agregoAlCarrito(String producto) {
    Actor actor = OnStage.theActorInTheSpotlight();
    ExplicitHelpers.addSpecificProductToCart(actor, producto);
    ExplicitValidations.validateProductIsInCart(actor, producto);
}
```

### Ejemplo 3: Validación de carrito
```java
@Entonces("el carrito debe tener {int} productos")
public void elCarritoDebeTenerProductos(Integer cantidad) {
    Actor actor = OnStage.theActorInTheSpotlight();
    ExplicitValidations.validateCartCount(actor, cantidad);
}
```

## Configuración

Para personalizar el comportamiento de los métodos explícitos, modifica las constantes en `ExplicitConfig.java`:

```java
// Cambiar timeout por defecto
public static final int DEFAULT_ELEMENT_TIMEOUT = 15;

// Habilitar modo debug
public static final boolean ENABLE_DEBUG_MODE = true;

// Cambiar usuario por defecto
public static final String DEFAULT_USERNAME = "custom_user";
```

## Troubleshooting

### Problemas comunes:

1. **Timeout de elementos**: Aumentar el timeout en `ExplicitConfig.java`
2. **Elementos no encontrados**: Verificar que el target esté correctamente definido
3. **Validaciones fallidas**: Revisar los mensajes de error en `ExplicitValidations.java`
4. **Performance lenta**: Ajustar timeouts y configuraciones de espera

### Logs útiles:

- Habilitar `ENABLE_VERBOSE_LOGGING = true` para logs detallados
- Habilitar `ENABLE_DEBUG_MODE = true` para información de debugging
- Habilitar `ENABLE_ELEMENT_HIGHLIGHTING = true` para resaltar elementos
