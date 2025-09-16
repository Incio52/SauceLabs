package com.tuempresa.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {
    // Elementos de productos en el carrito
    public static final Target ITEM_NAME_IN_CART = Target.the("producto {0} en el carrito")
            .locatedBy("//div[contains(@class,'cart_item_label')]//div[contains(@class,'inventory_item_name') and normalize-space()='{0}']");
    
    public static final Target CART_ITEM = Target.the("item del carrito")
            .located(By.className("cart_item"));
    
    public static final Target REMOVE_BUTTON = Target.the("botón remover {0}")
            .locatedBy("//div[contains(@class,'cart_item_label')]//div[contains(@class,'inventory_item_name') and normalize-space()='{0}']/ancestor::div[contains(@class,'cart_item_label')]//button[contains(@class,'btn_secondary')]");
    
    public static final Target ALL_REMOVE_BUTTONS = Target.the("todos los botones remover")
            .located(By.className("btn_secondary"));
    
    // Botones de acción
    public static final Target CONTINUE_SHOPPING_BUTTON = Target.the("botón continuar comprando")
            .located(By.className("btn_secondary"));
    
    public static final Target CHECKOUT_BUTTON = Target.the("botón checkout")
            .located(By.className("btn_action"));
    
    // Formulario de checkout
    public static final Target CHECKOUT_FORM = Target.the("formulario de checkout")
            .located(By.className("checkout_info"));
    
    public static final Target FIRST_NAME_FIELD = Target.the("campo nombre")
            .located(By.id("first-name"));
    
    public static final Target LAST_NAME_FIELD = Target.the("campo apellido")
            .located(By.id("last-name"));
    
    public static final Target POSTAL_CODE_FIELD = Target.the("campo código postal")
            .located(By.id("postal-code"));
    
    // Botones de checkout específicos
    public static final Target CONTINUE_BUTTON = Target.the("botón continue")
            .located(By.xpath("/html/body/div/div/div/div[2]/div/form/div[2]/input"));
    
    public static final Target FINISH_BUTTON = Target.the("botón finish")
            .located(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[9]/button[2]"));
    
    public static final Target ORDER_SUMMARY = Target.the("resumen del pedido")
            .located(By.cssSelector(".summary_info, .cart_summary, .summary"));
}
