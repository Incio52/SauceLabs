package com.tuempresa.automation.questions;

import com.tuempresa.automation.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class InventoryTitle implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(InventoryPage.TITLE).answeredBy(actor).trim();
    }
    public static Question<String> value(){ return new InventoryTitle(); }
}
