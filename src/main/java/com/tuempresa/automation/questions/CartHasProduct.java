package com.tuempresa.automation.questions;

import com.tuempresa.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CartHasProduct implements Question<Boolean> {
    private final String name;
    public CartHasProduct(String name){ this.name = name; }
    @Override
    public Boolean answeredBy(Actor actor) {
        return CartPage.ITEM_NAME_IN_CART.of(name).resolveFor(actor).isVisible();
    }
    public static Question<Boolean> named(String name){ return new CartHasProduct(name); }
}
