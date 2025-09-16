package com.tuempresa.automation.utils;

/**
 * Clase con constantes utilizadas en los tests
 */
public class TestConstants {
    
    // URLs
    public static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";
    
    // Credenciales de usuario
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_USER = "performance_glitch_user";
    public static final String PASSWORD = "secret_sauce";
    
    // Nombres de productos
    public static final String BACKPACK_PRODUCT = "Sauce Labs Backpack";
    public static final String BIKE_LIGHT_PRODUCT = "Sauce Labs Bike Light";
    public static final String BOLT_TSHIRT_PRODUCT = "Sauce Labs Bolt T-Shirt";
    public static final String FLEECE_JACKET_PRODUCT = "Sauce Labs Fleece Jacket";
    public static final String ONESIE_PRODUCT = "Sauce Labs Onesie";
    public static final String RED_TSHIRT_PRODUCT = "Test.allTheThings() T-Shirt (Red)";
    
    // Títulos de páginas
    public static final String INVENTORY_PAGE_TITLE = "Products";
    public static final String CART_PAGE_TITLE = "Your Cart";
    public static final String CHECKOUT_PAGE_TITLE = "Checkout: Your Information";
    
    // Mensajes de error
    public static final String LOCKED_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    public static final String INVALID_CREDENTIALS_ERROR = "Epic sadface: Username and password do not match any user in this service";
    public static final String REQUIRED_USERNAME_ERROR = "Epic sadface: Username is required";
    public static final String REQUIRED_PASSWORD_ERROR = "Epic sadface: Password is required";
    
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 20;
    
    // Cantidades
    public static final int ZERO_ITEMS = 0;
    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEMS = 2;
    public static final int THREE_ITEMS = 3;
    
    // Precios de productos (para validaciones futuras)
    public static final String BACKPACK_PRICE = "$29.99";
    public static final String BIKE_LIGHT_PRICE = "$9.99";
    public static final String BOLT_TSHIRT_PRICE = "$15.99";
    public static final String FLEECE_JACKET_PRICE = "$49.99";
    public static final String ONESIE_PRICE = "$7.99";
    public static final String RED_TSHIRT_PRICE = "$15.99";
    
    // Tags para Cucumber
    public static final String SMOKE_TAG = "@smoke";
    public static final String REGRESSION_TAG = "@regression";
    public static final String CART_TAG = "@cart";
    public static final String LOGIN_TAG = "@login";
    public static final String FUNCTIONAL_TAG = "@functional";
    public static final String VIEW_CART_TAG = "@view_cart";
    public static final String ADD_PRODUCT_TAG = "@add_product";
    
    // Nombres de actores
    public static final String QA_ACTOR = "QA";
    public static final String TESTER_ACTOR = "Tester";
    public static final String USER_ACTOR = "User";
    
    // Configuraciones del navegador
    public static final String CHROME_BROWSER = "chrome";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String EDGE_BROWSER = "edge";
    
    // Configuraciones de Serenity
    public static final String SERENITY_OUTPUT_DIR = "target/serenity";
    public static final String PROJECT_NAME = "Serenity Screenplay - SauceDemo";
}
