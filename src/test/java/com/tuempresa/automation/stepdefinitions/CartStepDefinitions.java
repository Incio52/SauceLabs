package com.tuempresa.automation.stepdefinitions;

import com.tuempresa.automation.utils.ExplicitHelpers;
import com.tuempresa.automation.utils.ExplicitValidations;
import com.tuempresa.automation.utils.AllureUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Map;

public class CartStepDefinitions {

    @Managed(driver="chrome")
    WebDriver hisBrowser;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        Actor qa = OnStage.theActorCalled("QA");
        qa.can(BrowseTheWeb.with(hisBrowser));
    }

    @Dado("que estoy logueado como {string} con contraseña {string}")
    public void queEstoyLogueadoComoContrasena(String user, String pass){
        // AllureAnnotations.cartFeature();
        // AllureAnnotations.functionalTest();
        // AllureAnnotations.environmentInfo();
        
        Actor actor = OnStage.theActorInTheSpotlight();
        
        AllureUtils.step("Abrir la aplicación SauceDemo", () -> {
            ExplicitHelpers.openApplicationAndWait(actor);
            AllureUtils.addCurrentUrl(actor);
            AllureUtils.addPageTitle(actor);
        });
        
        AllureUtils.step("Realizar login con credenciales: " + user, () -> {
            ExplicitHelpers.loginWithCredentials(actor, user, pass);
            AllureUtils.addScreenshot(actor, "Login realizado");
        });
        
        AllureUtils.step("Validar login exitoso", () -> {
            ExplicitValidations.validateLoginSuccessful(actor);
            AllureUtils.addScreenshot(actor, "Página de inventario");
        });
    }

    @Cuando("agrego el producto {string} al carrito")
    public void agregoElProductoAlCarrito(String producto){
        AllureUtils.step("Agregar producto al carrito: " + producto, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.addSpecificProductToCart(actor, producto);
            AllureUtils.addScreenshot(actor, "Producto agregado al carrito");
            AllureUtils.addText("Producto agregado", producto);
        });
    }

    @Entonces("el carrito debería mostrar {int} producto")
    public void elCarritoDeberiaMostrarProducto(Integer cantidad){
        AllureUtils.step("Validar contador del carrito: " + cantidad, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartCount(actor, cantidad);
            AllureUtils.addScreenshot(actor, "Contador del carrito");
            AllureUtils.addValidationStep("Contador del carrito", cantidad.toString(), 
                                        String.valueOf(ExplicitHelpers.getCartItemCount(actor)), true);
        });
    }

    @Dado("que agregué el producto {string} al carrito")
    public void queAgregueElProductoAlCarrito(String producto){
        AllureUtils.step("Preparar producto en el carrito: " + producto, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.addSpecificProductToCart(actor, producto);
            AllureUtils.addScreenshot(actor, "Producto preparado en el carrito");
        });
    }

    @Cuando("navego al carrito")
    public void navegoAlCarrito(){
        AllureUtils.step("Navegar al carrito de compras", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.navigateToCart(actor);
            AllureUtils.addScreenshot(actor, "Página del carrito");
            AllureUtils.addCurrentUrl(actor);
        });
    }

    @Entonces("debería ver el producto {string} en el carrito")
    public void deberiaVerElProductoEnElCarrito(String producto){
        AllureUtils.step("Verificar producto en el carrito: " + producto, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateProductIsInCart(actor, producto);
            AllureUtils.addScreenshot(actor, "Producto verificado en el carrito");
            AllureUtils.addValidationStep("Producto en carrito", producto, producto, true);
        });
    }

    // Nuevos step definitions para los escenarios mejorados

    @Dado("estoy en la página del inventario de productos")
    public void estoyEnLaPaginaDelInventarioDeProductos() {
        AllureUtils.step("Verificar que estoy en la página del inventario", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateLoginSuccessful(actor);
            AllureUtils.addScreenshot(actor, "Página del inventario");
        });
    }

    @Dado("que veo la lista de productos disponibles")
    public void queVeoLaListaDeProductosDisponibles() {
        AllureUtils.step("Verificar lista de productos disponibles", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateInventoryPageLoaded(actor);
            AllureUtils.addScreenshot(actor, "Lista de productos");
        });
    }

    @Entonces("el botón del producto debe cambiar a {string}")
    public void elBotonDelProductoDebeCambiarA(String textoBoton) {
        AllureUtils.step("Verificar cambio de botón a: " + textoBoton, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateButtonTextChanged(actor, textoBoton);
            AllureUtils.addScreenshot(actor, "Botón cambiado");
        });
    }

    @Entonces("el ícono del carrito debe mostrar el número {string}")
    public void elIconoDelCarritoDebeMostrarElNumero(String numero) {
        AllureUtils.step("Verificar contador del carrito: " + numero, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartBadgeCount(actor, Integer.parseInt(numero));
            AllureUtils.addScreenshot(actor, "Contador del carrito");
        });
    }

    @Cuando("agrego los siguientes productos al carrito:")
    public void agregoLosSiguientesProductosAlCarrito(DataTable dataTable) {
        List<Map<String, String>> productos = dataTable.asMaps(String.class, String.class);
        AllureUtils.step("Agregar múltiples productos al carrito", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            for (Map<String, String> producto : productos) {
                String nombreProducto = producto.get("producto");
                ExplicitHelpers.addSpecificProductToCart(actor, nombreProducto);
                AllureUtils.addText("Producto agregado", nombreProducto);
            }
            AllureUtils.addScreenshot(actor, "Múltiples productos agregados");
        });
    }

    @Entonces("el carrito debería mostrar {int} productos")
    public void elCarritoDeberiaMostrarProductos(Integer cantidad) {
        AllureUtils.step("Verificar cantidad de productos en el carrito: " + cantidad, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartCount(actor, cantidad);
            AllureUtils.addScreenshot(actor, "Cantidad de productos en el carrito");
        });
    }

    @Entonces("debería ver el botón {string}")
    public void deberiaVerElBoton(String nombreBoton) {
        AllureUtils.step("Verificar botón: " + nombreBoton, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateButtonIsVisible(actor, nombreBoton);
            AllureUtils.addScreenshot(actor, "Botón verificado: " + nombreBoton);
        });
    }

    @Cuando("remuevo el producto {string} del carrito")
    public void remuevoElProductoDelCarrito(String producto) {
        AllureUtils.step("Remover producto del carrito: " + producto, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.removeProductFromCart(actor, producto);
            AllureUtils.addScreenshot(actor, "Producto removido del carrito");
        });
    }

    @Entonces("no debería ver el producto {string} en el carrito")
    public void noDeberiaVerElProductoEnElCarrito(String producto) {
        AllureUtils.step("Verificar que el producto no está en el carrito: " + producto, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateProductNotInCart(actor, producto);
            AllureUtils.addScreenshot(actor, "Producto no está en el carrito");
        });
    }

    @Cuando("remuevo todos los productos del carrito")
    public void remuevoTodosLosProductosDelCarrito() {
        AllureUtils.step("Remover todos los productos del carrito", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.removeAllProductsFromCart(actor);
            AllureUtils.addScreenshot(actor, "Carrito vaciado");
        });
    }

    @Entonces("el carrito debería estar vacío")
    public void elCarritoDeberiaEstarVacio() {
        AllureUtils.step("Verificar que el carrito está vacío", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartIsEmpty(actor);
            AllureUtils.addScreenshot(actor, "Carrito vacío");
        });
    }

    @Entonces("no debería ver ningún producto en el carrito")
    public void noDeberiaVerNingunProductoEnElCarrito() {
        AllureUtils.step("Verificar que no hay productos en el carrito", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateNoProductsInCart(actor);
            AllureUtils.addScreenshot(actor, "Sin productos en el carrito");
        });
    }

    @Entonces("el ícono del carrito no debería mostrar ningún número")
    public void elIconoDelCarritoNoDeberiaMostrarNingunNumero() {
        AllureUtils.step("Verificar que el ícono del carrito no muestra número", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartBadgeNotVisible(actor);
            AllureUtils.addScreenshot(actor, "Ícono del carrito sin número");
        });
    }

    @Cuando("hago clic en {string}")
    public void hagoClicEn(String nombreBoton) {
        AllureUtils.step("Hacer clic en: " + nombreBoton, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.clickButton(actor, nombreBoton);
            AllureUtils.addScreenshot(actor, "Clic en: " + nombreBoton);
        });
    }

    @Entonces("debería regresar a la página del inventario")
    public void deberiaRegresarALaPaginaDelInventario() {
        AllureUtils.step("Verificar regreso a la página del inventario", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateInventoryPageLoaded(actor);
            AllureUtils.addScreenshot(actor, "Página del inventario");
        });
    }

    @Entonces("el carrito debería mantener los productos agregados")
    public void elCarritoDeberiaMantenerLosProductosAgregados() {
        AllureUtils.step("Verificar que el carrito mantiene los productos", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCartMaintainsProducts(actor);
            AllureUtils.addScreenshot(actor, "Carrito con productos mantenidos");
        });
    }

    @Entonces("debería ser redirigido a la página de información de checkout")
    public void deberiaSerRedirigidoALaPaginaDeInformacionDeCheckout() {
        AllureUtils.step("Verificar redirección a checkout", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCheckoutPageLoaded(actor);
            AllureUtils.addScreenshot(actor, "Página de checkout");
        });
    }

    @Entonces("debería ver el formulario de datos personales")
    public void deberiaVerElFormularioDeDatosPersonales() {
        AllureUtils.step("Verificar formulario de datos personales", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validatePersonalInfoForm(actor);
            AllureUtils.addScreenshot(actor, "Formulario de datos personales");
        });
    }

    @Entonces("debería ver el resumen del pedido")
    public void deberiaVerElResumenDelPedido() {
        AllureUtils.step("Verificar resumen del pedido", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateOrderSummary(actor);
            AllureUtils.addScreenshot(actor, "Resumen del pedido");
        });
    }

    @Cuando("completo el formulario de checkout con {string}, {string} y {string}")
    public void completoElFormularioDeCheckoutCon(String firstName, String lastName, String postalCode) {
        AllureUtils.step("Completar formulario de checkout", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.fillCheckoutForm(actor, firstName, lastName, postalCode);
            AllureUtils.addScreenshot(actor, "Formulario de checkout completado");
            AllureUtils.addText("Datos ingresados", "Nombre: " + firstName + ", Apellido: " + lastName + ", Código Postal: " + postalCode);
        });
    }

    @Entonces("debería ver el botón {string} en la página de checkout")
    public void deberiaVerElBotonEnLaPaginaDeCheckout(String nombreBoton) {
        AllureUtils.step("Verificar botón en checkout: " + nombreBoton, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateCheckoutButtonIsVisible(actor, nombreBoton);
            AllureUtils.addScreenshot(actor, "Botón verificado en checkout: " + nombreBoton);
        });
    }

    @Cuando("hago clic en {string} en la página de checkout")
    public void hagoClicEnEnLaPaginaDeCheckout(String nombreBoton) {
        AllureUtils.step("Hacer clic en checkout: " + nombreBoton, () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitHelpers.clickCheckoutButton(actor, nombreBoton);
            AllureUtils.addScreenshot(actor, "Clic en checkout: " + nombreBoton);
        });
    }

    @Entonces("debería ver la página de confirmación de pedido")
    public void deberiaVerLaPaginaDeConfirmacionDePedido() {
        AllureUtils.step("Verificar página de confirmación de pedido", () -> {
            Actor actor = OnStage.theActorInTheSpotlight();
            ExplicitValidations.validateOrderConfirmationPage(actor);
            AllureUtils.addScreenshot(actor, "Página de confirmación de pedido");
        });
    }
}
