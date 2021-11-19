package com.nixsolutions.uiTests.basketPopUp;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;

public class BasketPopUp {
    SoftAssert assertTest = new SoftAssert();

    public void addToBasket(String text) {
        SelenideElement addGood = $(By.xpath("//button[@class = 'buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']"))
                .should(Condition.exist, Condition.visible);
        addGood.click();
        assertTest.assertEquals($(By.className("modal__heading")).should(Condition.appear).getText(), "Корзина");
        SelenideElement goodIsAdded = $(By.xpath("//a[@class = 'cart-product__title']"));
        assertTest.assertEquals(goodIsAdded.getAttribute("title").trim(), text);
        assertTest.assertAll();
    }

    public void removeFromBasket() {
        SelenideElement cartProductActions = $(By.xpath("//button[@id='cartProductActions0']")).should(Condition.visible);
        cartProductActions.click();
        SelenideElement remove = $(By.xpath("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']")).should(Condition.visible);
        remove.click();
        SelenideElement message = $(By.xpath("//h4[@class='cart-dummy__heading']")).should(Condition.visible);
        assertTest.assertEquals("Корзина пуста", message.getText());
    }

    public void orderForm() {
        SelenideElement zakazOrderButton = $(By.xpath("//*[@class = 'button button_size_large button_color_green cart-receipt__submit ng-star-inserted']")).should(Condition.enabled);
        zakazOrderButton.click();
        SelenideElement title = $(By.xpath("//*[@class = 'checkout-heading ng-star-inserted']")).should(Condition.visible);
        assertTest.assertEquals(title.getText(), "Оформление заказа");
    }

    public void selectSection(){
        SelenideElement el1 = $(By.xpath("//a[@class = 'menu-categories__link' and text() = 'Ноутбуки и компьютеры']")).should(Condition.enabled);
        el1.click();
        SelenideElement el2 = $(By.xpath("//a[@class = 'tile-cats__heading tile-cats__heading_type_center ng-star-inserted' and text() = ' Компьютеры ']")).should(Condition.enabled);
        el2.click();
    }

    public void search(String searchData){
        SelenideElement searchField = $(By.xpath("//*[@class='search-form__input ng-untouched ng-pristine ng-valid']")).should(Condition.enabled);
        searchField.sendKeys(searchData);
        SelenideElement searchButton = $(By.xpath("//*[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']")).should(Condition.visible);
        searchButton.click();
        SelenideElement result = $(By.xpath("//*[@class='catalog-heading ng-star-inserted']")).should(Condition.appear);
        assertTest.assertEquals(result.getText(), "«Asus»");
    }
}
