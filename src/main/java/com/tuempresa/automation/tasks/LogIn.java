package com.tuempresa.automation.tasks;

import com.tuempresa.automation.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LogIn {
    public static Performable with(String username, String password) {
        return Task.where("{0} inicia sesión con credenciales",
            WaitUntil.the(LoginPage.USERNAME, isVisible()).forNoMoreThan(10).seconds(),
            Enter.theValue(username).into(LoginPage.USERNAME),
            Enter.theValue(password).into(LoginPage.PASSWORD).thenHit(Keys.ENTER)
        );
    }
}
