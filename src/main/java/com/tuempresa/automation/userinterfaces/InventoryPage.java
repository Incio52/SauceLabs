package com.tuempresa.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryPage {
    public static final Target TITLE = Target.the("título de inventario")
            .located(By.cssSelector(".title, .inventory"));
    public static final Target CART_ICON = Target.the("icono del carrito")
            .located(By.cssSelector(".shopping_cart_link"));
    public static final Target CART_BADGE = Target.the("contador del carrito")
            .located(By.cssSelector(".shopping_cart_badge"));

    // Contenedor de productos
    public static final Target PRODUCT_CONTAINER = Target.the("contenedor de productos")
            .located(By.className("inventory_container"));

    // Botón "Add to cart" para un producto por nombre exacto
    public static final Target ADD_TO_CART_BY_NAME = Target.the("agregar {0} al carrito")
            .locatedBy("//div[@class='inventory_item' and .//div[contains(@class,'inventory_item_name')][normalize-space()='{0}']]//button[contains(@data-test,'add-to-cart')]");
    
    // Botón "Remove" por si ya está añadido
    public static final Target REMOVE_BY_NAME = Target.the("remover {0} del carrito")
            .locatedBy("//div[@class='inventory_item' and .//div[contains(@class,'inventory_item_name')][normalize-space()='{0}']]//button[contains(@data-test,'remove')]");

    // Botón genérico para agregar/quitar del carrito
    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar al carrito")
            .located(By.cssSelector("button[data-test*='add-to-cart']"));

    // Menú hamburguesa
    public static final Target MENU_BUTTON = Target.the("botón del menú")
            .located(By.id("react-burger-menu-btn"));

    // Lista de productos
    public static final Target PRODUCT_LIST = Target.the("lista de productos")
            .located(By.className("inventory_list"));

    // Items individuales de productos
    public static final Target PRODUCT_ITEMS = Target.the("items de productos")
            .located(By.className("inventory_item"));
         
            

}
