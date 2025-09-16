package com.tuempresa.automation.utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import com.tuempresa.automation.userinterfaces.LoginPage;
import com.tuempresa.automation.userinterfaces.InventoryPage;
import com.tuempresa.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

/**
 * Clase con helpers explícitos para operaciones comunes
 */
public class ExplicitHelpers {

    /**
     * Helper para abrir la aplicación y esperar a que cargue
     * @param actor El actor que ejecuta la acción
     */
    public static void openApplicationAndWait(Actor actor) {
        actor.attemptsTo(
            Open.url(TestConstants.SAUCE_DEMO_URL),
            WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds()
        );
    }

    /**
     * Helper para realizar login con credenciales estándar
     * @param actor El actor que ejecuta la acción
     */
    public static void loginWithStandardUser(Actor actor) {
        loginWithCredentials(actor, TestConstants.STANDARD_USER, TestConstants.PASSWORD);
    }

    /**
     * Helper para realizar login con credenciales específicas
     * @param actor El actor que ejecuta la acción
     * @param username Nombre de usuario
     * @param password Contraseña
     */
    public static void loginWithCredentials(Actor actor, String username, String password) {
        actor.attemptsTo(
            WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds(),
            Enter.theValue(username).into(LoginPage.USERNAME),
            Enter.theValue(password).into(LoginPage.PASSWORD).thenHit(Keys.ENTER),
            WaitUntil.the(InventoryPage.TITLE, isVisible()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds()
        );
    }

    /**
     * Helper para agregar un producto específico al carrito
     * @param actor El actor que ejecuta la acción
     * @param productName Nombre del producto
     */
    public static void addSpecificProductToCart(Actor actor, String productName) {
        actor.attemptsTo(
            WaitUntil.the(InventoryPage.ADD_TO_CART_BY_NAME.of(productName), isClickable())
                    .forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds(),
            Click.on(InventoryPage.ADD_TO_CART_BY_NAME.of(productName))
        );
    }

    /**
     * Helper para agregar múltiples productos al carrito
     * @param actor El actor que ejecuta la acción
     * @param productNames Array de nombres de productos
     */
    public static void addMultipleProductsToCart(Actor actor, String... productNames) {
        for (String productName : productNames) {
            addSpecificProductToCart(actor, productName);
        }
    }

    /**
     * Helper para navegar al carrito
     * @param actor El actor que ejecuta la acción
     */
    public static void navigateToCart(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(InventoryPage.CART_ICON, isClickable()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds(),
            Click.on(InventoryPage.CART_ICON)
        );
    }

    /**
     * Helper para verificar si un elemento es visible
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @return true si el elemento es visible
     */
    public static boolean isElementVisible(Actor actor, net.serenitybdd.screenplay.targets.Target target) {
        return actor.asksFor(Visibility.of(target));
    }

    /**
     * Helper para obtener el texto de un elemento
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @return El texto del elemento
     */
    public static String getElementText(Actor actor, net.serenitybdd.screenplay.targets.Target target) {
        return actor.asksFor(Text.of(target));
    }

    /**
     * Helper para esperar a que un elemento sea visible
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @param timeoutSeconds Tiempo de espera en segundos
     */
    public static void waitForElementToBeVisible(Actor actor, net.serenitybdd.screenplay.targets.Target target, int timeoutSeconds) {
        actor.attemptsTo(
            WaitUntil.the(target, isVisible()).forNoMoreThan(timeoutSeconds).seconds()
        );
    }

    /**
     * Helper para esperar a que un elemento sea clickeable
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @param timeoutSeconds Tiempo de espera en segundos
     */
    public static void waitForElementToBeClickable(Actor actor, net.serenitybdd.screenplay.targets.Target target, int timeoutSeconds) {
        actor.attemptsTo(
            WaitUntil.the(target, isClickable()).forNoMoreThan(timeoutSeconds).seconds()
        );
    }

    /**
     * Helper para hacer scroll a un elemento
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     */
    public static void scrollToElement(Actor actor, net.serenitybdd.screenplay.targets.Target target) {
        // Implementar scroll si es necesario
        actor.attemptsTo(
            WaitUntil.the(target, isVisible()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds()
        );
    }

    /**
     * Helper para refrescar la página
     * @param actor El actor que ejecuta la acción
     */
    public static void refreshPage(Actor actor) {
        BrowseTheWeb.as(actor).getDriver().navigate().refresh();
    }

    /**
     * Helper para navegar hacia atrás
     * @param actor El actor que ejecuta la acción
     */
    public static void navigateBack(Actor actor) {
        BrowseTheWeb.as(actor).getDriver().navigate().back();
    }

    /**
     * Helper para navegar hacia adelante
     * @param actor El actor que ejecuta la acción
     */
    public static void navigateForward(Actor actor) {
        BrowseTheWeb.as(actor).getDriver().navigate().forward();
    }

    /**
     * Helper para obtener la URL actual
     * @param actor El actor que ejecuta la acción
     * @return La URL actual
     */
    public static String getCurrentUrl(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
    }

    /**
     * Helper para obtener el título de la página
     * @param actor El actor que ejecuta la acción
     * @return El título de la página
     */
    public static String getPageTitle(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getTitle();
    }

    /**
     * Helper para tomar una captura de pantalla
     * @param actor El actor que ejecuta la acción
     * @param screenshotName Nombre de la captura de pantalla
     */
    public static void takeScreenshot(Actor actor, String screenshotName) {
        // Serenity maneja automáticamente las capturas de pantalla
        // Este método puede ser usado para forzar una captura en un momento específico
        System.out.println("Taking screenshot: " + screenshotName);
        // La captura de pantalla se maneja automáticamente por Serenity
    }

    /**
     * Helper para limpiar el carrito (remover todos los productos)
     * @param actor El actor que ejecuta la acción
     */
    public static void clearCart(Actor actor) {
        // Navegar al carrito
        navigateToCart(actor);
        
        // Implementar lógica para remover todos los productos
        // Esto dependería de la implementación específica de la página del carrito
    }

    /**
     * Helper para verificar si el carrito está vacío
     * @param actor El actor que ejecuta la acción
     * @return true si el carrito está vacío
     */
    public static boolean isCartEmpty(Actor actor) {
        return !isElementVisible(actor, InventoryPage.CART_BADGE);
    }

    /**
     * Helper para obtener el número de productos en el carrito
     * @param actor El actor que ejecuta la acción
     * @return Número de productos en el carrito
     */
    public static int getCartItemCount(Actor actor) {
        if (isCartEmpty(actor)) {
            return 0;
        }
        String countText = getElementText(actor, InventoryPage.CART_BADGE);
        return Integer.parseInt(countText);
    }

    /**
     * Helper para verificar si un producto específico está en el carrito
     * @param actor El actor que ejecuta la acción
     * @param productName Nombre del producto
     * @return true si el producto está en el carrito
     */
    public static boolean isProductInCart(Actor actor, String productName) {
        navigateToCart(actor);
        return isElementVisible(actor, CartPage.ITEM_NAME_IN_CART.of(productName));
    }

    /**
     * Helper para remover un producto específico del carrito
     * @param actor El actor que ejecuta la acción
     * @param productName Nombre del producto a remover
     */
    public static void removeProductFromCart(Actor actor, String productName) {
        Target removeButton = CartPage.REMOVE_BUTTON.of(productName);
        Click.on(removeButton).performAs(actor);
    }

    /**
     * Helper para remover todos los productos del carrito
     * @param actor El actor que ejecuta la acción
     */
    public static void removeAllProductsFromCart(Actor actor) {
        // Remover todos los productos del carrito
        boolean hasProducts = true;
        int attempts = 0;
        while (hasProducts && attempts < 10) {
            try {
                // Verificar si hay productos en el carrito
                hasProducts = actor.asksFor(Visibility.of(CartPage.CART_ITEM));
                if (hasProducts) {
                    // Hacer clic en el primer botón remove disponible
                    Click.on(CartPage.ALL_REMOVE_BUTTONS).performAs(actor);
                    // Esperar un momento para que se actualice la página
                    Thread.sleep(500);
                }
                attempts++;
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Helper para hacer clic en un botón específico
     * @param actor El actor que ejecuta la acción
     * @param buttonName Nombre del botón
     */
    public static void clickButton(Actor actor, String buttonName) {
        switch (buttonName) {
            case "Continue Shopping":
                Click.on(CartPage.CONTINUE_SHOPPING_BUTTON).performAs(actor);
                break;
            case "Checkout":
                Click.on(CartPage.CHECKOUT_BUTTON).performAs(actor);
                break;
            default:
                throw new IllegalArgumentException("Botón no reconocido: " + buttonName);
        }
    }

    /**
     * Helper para llenar el formulario de checkout
     * @param actor El actor que ejecuta la acción
     * @param firstName Nombre
     * @param lastName Apellido
     * @param postalCode Código postal
     */
    public static void fillCheckoutForm(Actor actor, String firstName, String lastName, String postalCode) {
        actor.attemptsTo(
            WaitUntil.the(CartPage.FIRST_NAME_FIELD, isVisible()).forNoMoreThan(TestConstants.DEFAULT_TIMEOUT).seconds(),
            Enter.theValue(firstName).into(CartPage.FIRST_NAME_FIELD),
            Enter.theValue(lastName).into(CartPage.LAST_NAME_FIELD),
            Enter.theValue(postalCode).into(CartPage.POSTAL_CODE_FIELD)
        );
    }

    /**
     * Helper para hacer clic en botones específicos de checkout
     * @param actor El actor que ejecuta la acción
     * @param buttonName Nombre del botón
     */
    public static void clickCheckoutButton(Actor actor, String buttonName) {
        switch (buttonName) {
            case "Continue":
                Click.on(CartPage.CONTINUE_BUTTON).performAs(actor);
                break;
            case "Finish":
                Click.on(CartPage.FINISH_BUTTON).performAs(actor);
                break;
            case "Cancel":
                // Buscar el botón Cancel en la página de checkout
                Target cancelButton = Target.the("botón cancel")
                        .located(By.className("btn_secondary"));
                Click.on(cancelButton).performAs(actor);
                break;
            default:
                throw new IllegalArgumentException("Botón de checkout no reconocido: " + buttonName);
        }
    }
}
