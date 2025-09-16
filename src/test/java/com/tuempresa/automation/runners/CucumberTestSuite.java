package com.tuempresa.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.tuempresa.automation.stepdefinitions",
    plugin = {"pretty"},
    tags = "@cart"
)
public class CucumberTestSuite {}
