package com.tuempresa.automation.questions;

import com.tuempresa.automation.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CartBadgeCount implements Question<Integer> {
    @Override
    public Integer answeredBy(Actor actor) {
        if (InventoryPage.CART_BADGE.resolveFor(actor).isVisible()) {
            try {
                return Integer.parseInt(Text.of(InventoryPage.CART_BADGE).answeredBy(actor).trim());
            } catch (NumberFormatException e){
                return 0;
            }
        }
        return 0;
    }
    public static Question<Integer> value(){ return new CartBadgeCount(); }
}
