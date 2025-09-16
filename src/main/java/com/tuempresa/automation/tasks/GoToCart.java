package com.tuempresa.automation.tasks;

import com.tuempresa.automation.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCart {
    public static Performable view(){
        return Task.where("{0} navega al carrito",
            WaitUntil.the(InventoryPage.CART_ICON, isVisible()).forNoMoreThan(10).seconds(),
            Click.on(InventoryPage.CART_ICON)
        );
    }
}
