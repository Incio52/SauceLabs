package com.tuempresa.automation.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenTheApplication {
    public static Performable atHomePage() {
        return Task.where("{0} abre la página de SauceDemo",
                Open.url("https://www.saucedemo.com/")
        );
    }
}
