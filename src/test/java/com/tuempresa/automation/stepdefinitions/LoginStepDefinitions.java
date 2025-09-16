package com.tuempresa.automation.stepdefinitions;

import com.tuempresa.automation.questions.DisplayedError;
import com.tuempresa.automation.questions.InventoryTitle;
import com.tuempresa.automation.tasks.LogIn;
import com.tuempresa.automation.tasks.OpenTheApplication;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class LoginStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver hisBrowser;

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
}
