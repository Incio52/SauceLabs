package com.tuempresa.automation.utils;

import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Utilidades para integración con Allure (versión simplificada)
 */
public class AllureUtils {

    /**
     * Añade un paso personalizado a Allure
     * @param stepName Nombre del paso
     * @param stepAction Acción a ejecutar
     */
    public static void step(String stepName, Runnable stepAction) {
        System.out.println("STEP: " + stepName);
        stepAction.run();
    }

    /**
     * Añade un paso con parámetros a Allure
     * @param stepName Nombre del paso con placeholders
     * @param parameters Parámetros para el paso
     * @param stepAction Acción a ejecutar
     */
    public static void step(String stepName, Object[] parameters, Runnable stepAction) {
        String formattedStepName = String.format(stepName, parameters);
        System.out.println("STEP: " + formattedStepName);
        stepAction.run();
    }

    /**
     * Añade una descripción al test actual
     * @param description Descripción del test
     */
    public static void addDescription(String description) {
        System.out.println("DESCRIPTION: " + description);
    }

    /**
     * Añade un screenshot al reporte
     * @param actor El actor que ejecuta la acción
     * @param name Nombre del screenshot
     */
    public static void addScreenshot(Actor actor, String name) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) BrowseTheWeb.as(actor).getDriver();
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            String fileName = "screenshot_" + UUID.randomUUID().toString() + ".png";
            
            // Crear el directorio si no existe
            java.nio.file.Path screenshotsDir = Paths.get("target/screenshots");
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }
            
            Files.write(Paths.get("target/screenshots/" + fileName), screenshot);
            System.out.println("SCREENSHOT: " + name + " saved as " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + e.getMessage());
        }
    }

    /**
     * Añade texto al reporte
     * @param name Nombre del texto
     * @param content Contenido del texto
     */
    public static void addText(String name, String content) {
        System.out.println("TEXT [" + name + "]: " + content);
    }

    /**
     * Añade la URL actual al reporte
     * @param actor El actor que ejecuta la acción
     */
    public static void addCurrentUrl(Actor actor) {
        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        System.out.println("CURRENT URL: " + currentUrl);
    }

    /**
     * Añade el título de la página al reporte
     * @param actor El actor que ejecuta la acción
     */
    public static void addPageTitle(Actor actor) {
        String pageTitle = BrowseTheWeb.as(actor).getDriver().getTitle();
        System.out.println("PAGE TITLE: " + pageTitle);
    }

    /**
     * Añade un paso de validación al reporte
     * @param validationName Nombre de la validación
     * @param expected Valor esperado
     * @param actual Valor actual
     * @param passed Si la validación pasó
     */
    public static void addValidationStep(String validationName, String expected, String actual, boolean passed) {
        String status = passed ? "PASSED" : "FAILED";
        System.out.println("VALIDATION [" + validationName + "]: " + status);
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual: " + actual);
    }

    /**
     * Añade información del entorno al reporte
     */
    public static void addEnvironmentInfo() {
        System.out.println("ENVIRONMENT INFO:");
        System.out.println("  Java Version: " + System.getProperty("java.version"));
        System.out.println("  OS: " + System.getProperty("os.name"));
        System.out.println("  User: " + System.getProperty("user.name"));
    }

    /**
     * Añade información del navegador al reporte
     * @param actor El actor que ejecuta la acción
     */
    public static void addBrowserInfo(Actor actor) {
        String browserName = BrowseTheWeb.as(actor).getDriver().getClass().getSimpleName();
        System.out.println("BROWSER INFO: " + browserName);
    }
}