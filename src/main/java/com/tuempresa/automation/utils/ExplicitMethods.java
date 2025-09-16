package com.tuempresa.automation.utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.questions.Visibility;
import com.tuempresa.automation.userinterfaces.LoginPage;
import com.tuempresa.automation.userinterfaces.InventoryPage;
import com.tuempresa.automation.userinterfaces.CartPage;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Clase con métodos explícitos para operaciones comunes en la aplicación
 */
public class ExplicitMethods {

    /**
     * Método explícito para abrir la aplicación
     * @param actor El actor que ejecuta la acción
     * @param url La URL de la aplicación
     */
    public static void openApplication(Actor actor, String url) {
        actor.attemptsTo(
            Open.url(url),
            WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /**
     * Método explícito para realizar login
     * @param actor El actor que ejecuta la acción
     * @param username Nombre de usuario
     * @param password Contraseña
     */
    public static void performLogin(Actor actor, String username, String password) {
        actor.attemptsTo(
            WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(10).seconds(),
            Enter.theValue(username).into(LoginPage.USERNAME),
            Enter.theValue(password).into(LoginPage.PASSWORD).thenHit(Keys.ENTER),
            WaitUntil.the(InventoryPage.TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /**
     * Método explícito para agregar producto al carrito
     * @param actor El actor que ejecuta la acción
     * @param productName Nombre del producto
     */
    public static void addProductToCart(Actor actor, String productName) {
        actor.attemptsTo(
            WaitUntil.the(InventoryPage.ADD_TO_CART_BY_NAME.of(productName), isClickable())
                    .forNoMoreThan(10).seconds(),
            Click.on(InventoryPage.ADD_TO_CART_BY_NAME.of(productName))
        );
    }

    /**
     * Método explícito para navegar al carrito
     * @param actor El actor que ejecuta la acción
     */
    public static void navigateToCart(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(InventoryPage.CART_ICON, isClickable()).forNoMoreThan(10).seconds(),
            Click.on(InventoryPage.CART_ICON)
        );
    }

    /**
     * Método explícito para verificar el título de la página
     * @param actor El actor que ejecuta la acción
     * @param expectedTitle Título esperado
     */
    public static void verifyPageTitle(Actor actor, String expectedTitle) {
        String actualTitle = actor.asksFor(Text.of(InventoryPage.TITLE));
        assertThat("El título de la página no coincide", actualTitle, equalTo(expectedTitle));
    }

    /**
     * Método explícito para verificar el contador del carrito
     * @param actor El actor que ejecuta la acción
     * @param expectedCount Número esperado de productos
     */
    public static void verifyCartCount(Actor actor, int expectedCount) {
        String actualCount = actor.asksFor(Text.of(InventoryPage.CART_BADGE));
        assertThat("El contador del carrito no coincide", 
                  Integer.parseInt(actualCount), equalTo(expectedCount));
    }

    /**
     * Método explícito para verificar que un producto está en el carrito
     * @param actor El actor que ejecuta la acción
     * @param productName Nombre del producto
     */
    public static void verifyProductInCart(Actor actor, String productName) {
        boolean isProductVisible = actor.asksFor(Visibility.of(CartPage.ITEM_NAME_IN_CART.of(productName)));
        assertThat("El producto " + productName + " no está en el carrito", isProductVisible);
    }

    /**
     * Método explícito para verificar mensaje de error
     * @param actor El actor que ejecuta la acción
     * @param expectedMessage Mensaje de error esperado
     */
    public static void verifyErrorMessage(Actor actor, String expectedMessage) {
        String actualMessage = actor.asksFor(Text.of(LoginPage.ERROR_MESSAGE));
        assertThat("El mensaje de error no coincide", actualMessage, equalTo(expectedMessage));
    }

    /**
     * Método explícito para limpiar el carrito
     * @param actor El actor que ejecuta la acción
     */
    public static void clearCart(Actor actor) {
        // Implementar lógica para limpiar el carrito si es necesario
        actor.attemptsTo(
            WaitUntil.the(InventoryPage.CART_ICON, isClickable()).forNoMoreThan(5).seconds()
        );
    }

    /**
     * Método explícito para verificar que el usuario está logueado
     * @param actor El actor que ejecuta la acción
     */
    public static void verifyUserIsLoggedIn(Actor actor) {
        boolean isInventoryVisible = actor.asksFor(Visibility.of(InventoryPage.TITLE));
        assertThat("El usuario no está logueado correctamente", isInventoryVisible);
    }

    /**
     * Método explícito para obtener el valor de un campo
     * @param actor El actor que ejecuta la acción
     * @param target El target del campo
     * @return El valor del campo
     */
    public static String getFieldValue(Actor actor, net.serenitybdd.screenplay.targets.Target target) {
        return actor.asksFor(Value.of(target));
    }

    /**
     * Método explícito para verificar que un elemento es visible
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @return true si el elemento es visible
     */
    public static boolean isElementVisible(Actor actor, net.serenitybdd.screenplay.targets.Target target) {
        return actor.asksFor(Visibility.of(target));
    }

    /**
     * Método explícito para esperar a que un elemento sea clickeable
     * @param actor El actor que ejecuta la acción
     * @param target El target del elemento
     * @param timeoutSeconds Tiempo de espera en segundos
     */
    public static void waitForElementToBeClickable(Actor actor, net.serenitybdd.screenplay.targets.Target target, int timeoutSeconds) {
        actor.attemptsTo(
            WaitUntil.the(target, isClickable()).forNoMoreThan(timeoutSeconds).seconds()
        );
    }
}
