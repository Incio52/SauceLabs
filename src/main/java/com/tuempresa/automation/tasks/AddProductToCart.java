package com.tuempresa.automation.tasks;

import com.tuempresa.automation.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart {
    public static Performable named(String productName){
        return Task.where("{0} agrega el producto " + productName + " al carrito",
            WaitUntil.the(InventoryPage.ADD_TO_CART_BY_NAME.of(productName), isVisible())
                    .forNoMoreThan(10).seconds(),
            Check.whether(InventoryPage.ADD_TO_CART_BY_NAME.of(productName)
                    .resolveFor(net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight()).isVisible())
                    .andIfSo(Click.on(InventoryPage.ADD_TO_CART_BY_NAME.of(productName)))
        );
    }
}
