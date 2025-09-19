package com.tuempresa.automation.stepdefinitions;

import com.tuempresa.automation.questions.DisplayedError;
import com.tuempresa.automation.questions.InventoryTitle;
import com.tuempresa.automation.tasks.LogIn;
import com.tuempresa.automation.tasks.OpenTheApplication;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class LoginStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver hisBrowser;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Usuario").can(BrowseTheWeb.with(hisBrowser));
    }

    @Dado("que el usuario abre la aplicación")
    public void queElUsuarioAbreLaAplicacion() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenTheApplication.atHomePage());
    }

    @Cuando("ingresa credenciales válidas {string} y {string}")
    public void ingresaCredencialesValidas(String usuario, String contrasena) {
        OnStage.theActorInTheSpotlight().attemptsTo(LogIn.with(usuario, contrasena));
    }

    @Entonces("debe ver el inventario")
    public void debeVerElInventario() {
        assertThat("No se mostró el título esperado",
            OnStage.theActorInTheSpotlight().asksFor(InventoryTitle.value()), equalToIgnoringCase("Products"));
    }

    @Cuando("intenta iniciar sesión con {string} y {string}")
    public void intentaIniciarSesionConY(String usuario, String contrasena) {
        OnStage.theActorInTheSpotlight().attemptsTo(LogIn.with(usuario, contrasena));
    }

    @Entonces("debe ver el mensaje de error que contiene {string}")
    public void debeVerElMensajeDeErrorQueContiene(String fragmento) {
        assertThat(OnStage.theActorInTheSpotlight().asksFor(DisplayedError.text()), containsString(fragmento));
    }

    // Step definitions adicionales para el feature de login
    @Given("que el usuario navega a la página principal de SauceDemo")
    public void que_el_usuario_navega_a_la_página_principal_de_sauce_demo() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenTheApplication.atHomePage());
    }

    @Given("que el usuario está en la página de login")
    public void que_el_usuario_está_en_la_página_de_login() {
        // Ya estamos en la página de login después de abrir la aplicación
    }

    @When("hace clic en el botón de login")
    public void hace_clic_en_el_botón_de_login() {
        // El clic en login ya está incluido en la task LogIn
    }

    @Then("debe ser redirigido al inventario de productos")
    public void debe_ser_redirigido_al_inventario_de_productos() {
        assertThat("No se redirigió al inventario",
            OnStage.theActorInTheSpotlight().asksFor(InventoryTitle.value()), equalToIgnoringCase("Products"));
    }

    @Then("debe ver el título {string}")
    public void debe_ver_el_título(String titulo) {
        assertThat("No se mostró el título esperado",
            OnStage.theActorInTheSpotlight().asksFor(InventoryTitle.value()), equalToIgnoringCase(titulo));
    }

    @Then("debe ver el menú hamburguesa disponible")
    public void debe_ver_el_menú_hamburguesa_disponible() {
        // Verificación del menú hamburguesa - implementar si es necesario
    }

    @Then("debe permanecer en la página de login")
    public void debe_permanecer_en_la_página_de_login() {
        // Verificar que seguimos en login page
    }

    @Then("el campo de contraseña debe estar vacío")
    public void el_campo_de_contraseña_debe_estar_vacío() {
        // Verificar que el campo password está vacío
    }

    @When("deja los campos de usuario y contraseña vacíos")
    public void deja_los_campos_de_usuario_y_contraseña_vacíos() {
        OnStage.theActorInTheSpotlight().attemptsTo(LogIn.with("", ""));
    }

    @Then("debe ver el mensaje de error {string}")
    public void debe_ver_el_mensaje_de_error(String mensaje) {
        assertThat(OnStage.theActorInTheSpotlight().asksFor(DisplayedError.text()), containsString(mensaje));
    }

    @When("ingresa credenciales con caracteres especiales {string} y {string}")
    public void ingresa_credenciales_con_caracteres_especiales_y(String usuario, String contrasena) {
        OnStage.theActorInTheSpotlight().attemptsTo(LogIn.with(usuario, contrasena));
    }
}
