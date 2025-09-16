@cart @shopping @ecommerce
Feature:  Gestión del Carrito de Compras - SauceDemo
  Como usuario autenticado de SauceDemo
  Quiero poder gestionar productos en mi carrito de compras
  Para realizar una compra exitosa

  Background:
    Given que estoy logueado como "standard_user" con contraseña "secret_sauce"
    And estoy en la página del inventario de productos

  @cart @add_product @smoke @critical
  Scenario:  Agregar un producto al carrito
    Given que veo la lista de productos disponibles
    When agrego el producto "Sauce Labs Backpack" al carrito
    Then el carrito debería mostrar 1 producto
    And el botón del producto debe cambiar a "Remove"
    And el ícono del carrito debe mostrar el número "1"

  @cart @add_multiple @functional @high
  Scenario Outline:  Agregar múltiples productos al carrito
    Given que veo la lista de productos disponibles
    When agrego los siguientes productos al carrito:
      | producto                    |
      | Sauce Labs Backpack         |
      | Sauce Labs Bike Light       |
      | Sauce Labs Bolt T-Shirt     |
    Then el carrito debería mostrar <cantidad> productos
    And el ícono del carrito debe mostrar el número "<cantidad>"

    Examples:
      | cantidad |
      | 3        |

  @cart @view_cart @functional @high
  Scenario:  Ver productos en el carrito
    Given que agregué el producto "Sauce Labs Backpack" al carrito
    And que agregué el producto "Sauce Labs Bike Light" al carrito
    When navego al carrito
    Then debería ver el producto "Sauce Labs Backpack" en el carrito
    And debería ver el producto "Sauce Labs Bike Light" en el carrito
    And el carrito debería mostrar 2 productos
    And debería ver el botón "Continue Shopping"
    And debería ver el botón "Checkout"

  @cart @remove_product @functional @medium
  Scenario:  Remover un producto del carrito
    Given que agregué el producto "Sauce Labs Backpack" al carrito
    And que agregué el producto "Sauce Labs Bike Light" al carrito
    When navego al carrito
    And remuevo el producto "Sauce Labs Backpack" del carrito
    Then el carrito debería mostrar 1 producto
    And no debería ver el producto "Sauce Labs Backpack" en el carrito
    And debería ver el producto "Sauce Labs Bike Light" en el carrito

  @cart @empty_cart @functional @medium
  Scenario: Vaciar el carrito completamente
    Given que agregué el producto "Sauce Labs Backpack" al carrito
    And que agregué el producto "Sauce Labs Bike Light" al carrito
    When navego al carrito
    And remuevo todos los productos del carrito
    Then el carrito debería estar vacío
    And no debería ver ningún producto en el carrito
    And el ícono del carrito no debería mostrar ningún número

  @cart @continue_shopping @functional @low
  Scenario:  Continuar comprando desde el carrito
    Given que agregué el producto "Sauce Labs Backpack" al carrito
    When navego al carrito
    And hago clic en "Continue Shopping"
    Then debería regresar a la página del inventario
    And el carrito debería mantener los productos agregados
    And el ícono del carrito debe mostrar el número "1"

  @cart @checkout @functional @high
  Scenario:  Completar proceso de checkout completo
    Given que agregué el producto "Sauce Labs Backpack" al carrito
    When navego al carrito
    And hago clic en "Checkout"
    Then debería ser redirigido a la página de información de checkout
    And debería ver el formulario de datos personales
    And debería ver el resumen del pedido
    When completo el formulario de checkout con "Juan", "Pérez" y "12345"
    And hago clic en "Continue" en la página de checkout
    Then debería ver el botón "Finish" en la página de checkout
    When hago clic en "Finish" en la página de checkout
    Then debería ver la página de confirmación de pedido
