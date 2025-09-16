package com.tuempresa.automation.questions;

import com.tuempresa.automation.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class DisplayedError implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        if (LoginPage.ERROR_MESSAGE.resolveFor(actor).isVisible()) {
            return Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor).trim();
        }
        return "";
    }
    public static Question<String> text() { return new DisplayedError(); }
}
