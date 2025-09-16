@login @authentication
Feature: Sistema de Autenticación - SauceDemo
  Como usuario de la aplicación SauceDemo
  Quiero poder autenticarme de forma segura
  Para acceder a las funcionalidades del sistema de inventario

  Background:
    Given que el usuario navega a la página principal de SauceDemo

  @login @positive @smoke @critical
  Scenario Outline:  Login exitoso con credenciales válidas
    Given que el usuario está en la página de login
    When ingresa credenciales válidas "<usuario>" y "<contrasena>"
    And hace clic en el botón de login
    Then debe ser redirigido al inventario de productos
    And debe ver el título "Products"
    And debe ver el menú hamburguesa disponible

    Examples:
      | usuario                | contrasena    | descripcion                    |
      | standard_user          | secret_sauce  | Usuario estándar válido        |
      | performance_glitch_user| secret_sauce  | Usuario con problemas de rendimiento |
      | problem_user           | secret_sauce  | Usuario con problemas conocidos |

  @login @negative @functional @high
  Scenario Outline:  Login fallido con credenciales inválidas
    Given que el usuario está en la página de login
    When intenta iniciar sesión con "<usuario>" y "<contrasena>"
    And hace clic en el botón de login
    Then debe permanecer en la página de login
    And debe ver el mensaje de error que contiene "<mensaje>"
    And el campo de contraseña debe estar vacío

    Examples:
      | usuario           | contrasena   | mensaje                             | tipo_error           |
      | locked_out_user   | secret_sauce | Sorry, this user has been locked out| Usuario bloqueado    |
      | standard_user     | wrong_pass   | Username and password do not match  | Contraseña incorrecta |
      | invalid_user      | secret_sauce | Username and password do not match  | Usuario inexistente  |
      | standard_user     | ""           | Username is required               | Campo vacío          |
      | ""                | secret_sauce | Password is required               | Campo vacío          |

  @login @edge @functional @medium
  Scenario:  Validación de campos obligatorios
    Given que el usuario está en la página de login
    When deja los campos de usuario y contraseña vacíos
    And hace clic en el botón de login
    Then debe ver el mensaje de error "Username is required"
    And debe permanecer en la página de login

  @login @security @functional @high
  Scenario: Intento de login con caracteres especiales
    Given que el usuario está en la página de login
    When ingresa credenciales con caracteres especiales "<usuario>" y "<contrasena>"
    And hace clic en el botón de login
    Then debe ver el mensaje de error que contiene "<mensaje>"

    Examples:
      | usuario        | contrasena     | mensaje                             |
      | <script>       | secret_sauce   | Username and password do not match  |
      | admin'--       | secret_sauce   | Username and password do not match  |
      | standard_user  | ' OR '1'='1    | Username and password do not match  |
