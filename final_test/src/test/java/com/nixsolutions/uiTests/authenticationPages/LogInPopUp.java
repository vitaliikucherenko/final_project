package com.nixsolutions.uiTests.authenticationPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;

public class LogInPopUp {
    public void LogIn(String login, String password) {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement lohInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        lohInButton.click();
        SelenideElement loginField = $(By.id("auth_email")).should(Condition.empty);
        loginField.sendKeys(login);
        SelenideElement passwordField = $(By.id("auth_pass")).should(Condition.empty);
        passwordField.sendKeys(password);
        assertTest.assertEquals(loginField.getAttribute("value"), login);
        assertTest.assertEquals(passwordField.getAttribute("value"), password);
        assertTest.assertAll();
    }

    public void titleOfLoginFields() {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement logInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        logInButton.click();
        SelenideElement loginField = $(By.xpath("//*[@class='form__label'] [@for='auth_email']")).should(Condition.visible);
        SelenideElement passwordField = $(By.xpath("//*[@class='form__label'] [@for='auth_pass']")).should(Condition.visible);
        assertTest.assertEquals(loginField.getText(), "Эл. почта или телефон");
        assertTest.assertEquals(passwordField.getText(), "Пароль");
        assertTest.assertAll();
    }

    public void validationLoginField() {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement lohInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        lohInButton.click();
        SelenideElement enterButton = $(By.xpath("//*[@class='button button--large button--green auth-modal__submit ng-star-inserted']"))
                .should(Condition.enabled);
        enterButton.click();
        SelenideElement loginFieldValidation = $(By.xpath("//*[@class='form__row validation_type_error']//p[@class='error-message ng-star-inserted']"))
                .should(Condition.visible);
        assertTest.assertEquals(loginFieldValidation.getText(), "Введен неверный адрес эл.почты или номер телефона");
        assertTest.assertEquals(loginFieldValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertAll();
    }

    public void validationPasswordField() {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement lohInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        lohInButton.click();
        SelenideElement enterButton = $(By.xpath("//*[@class='button button--large button--green auth-modal__submit ng-star-inserted']"))
                .should(Condition.enabled);
        enterButton.click();
        SelenideElement passwordFieldValidation = $(By.id("auth_pass"))
                .shouldHave(Condition.empty, Condition.visible);
        assertTest.assertEquals(passwordFieldValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
    }

    public void registerPopUp(String firstNameValue, String lastNameValue, String phoneValue, String emailValue, String passwordValue) {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement lohInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        lohInButton.click();
        SelenideElement registerLink = $(By.xpath("//*[@class='auth-modal__register-link button button--link ng-star-inserted']")).should(Condition.enabled);
        registerLink.click();
        SelenideElement firstName = $(By.id("registerUserName")).should(Condition.visible);
        SelenideElement lastName = $(By.id("registerUserSurname")).should(Condition.visible);
        SelenideElement phone = $(By.id("registerUserPhone")).should(Condition.visible);
        SelenideElement email = $(By.id("registerUserEmail")).should(Condition.visible);
        SelenideElement password = $(By.id("registerUserPassword")).should(Condition.visible);
        firstName.sendKeys(firstNameValue);
        lastName.sendKeys(lastNameValue);
        phone.sendKeys(phoneValue);
        email.sendKeys(emailValue);
        password.sendKeys(passwordValue);
        assertTest.assertEquals(firstName.getAttribute("value"), firstNameValue);
        assertTest.assertEquals(lastName.getAttribute("value"), lastNameValue);
        String phoneResult = "+380 " + phoneValue;
        assertTest.assertEquals(phone.getAttribute("value"), phoneResult);
        assertTest.assertEquals(email.getAttribute("value"), emailValue);
        assertTest.assertEquals(password.getAttribute("value"), passwordValue);
        assertTest.assertAll();
    }

    public void titleOfRegisterFields() {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement logInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        logInButton.click();
        SelenideElement registerLink = $(By.xpath("//*[@class='auth-modal__register-link button button--link ng-star-inserted']")).should(Condition.enabled);
        registerLink.click();
        SelenideElement firstName = $(By.xpath("//*[@class='form__label'][@for='registerUserName']")).should(Condition.visible);
        SelenideElement lastName = $(By.xpath("//*[@class='form__label'][@for='registerUserSurname']")).should(Condition.visible);
        SelenideElement phone = $(By.xpath("//*[@class='form__label'][@for='registerUserPhone']")).should(Condition.visible);
        SelenideElement email = $(By.xpath("//*[@class='form__label'][@for='registerUserEmail']")).should(Condition.visible);
        SelenideElement password = $(By.xpath("//*[@class='form__label'][@for='registerUserPassword']")).should(Condition.visible);
        assertTest.assertEquals(firstName.getText(), "Имя");
        assertTest.assertEquals(lastName.getText(), "Фамилия");
        assertTest.assertEquals(phone.getText(), "Номер телефона");
        assertTest.assertEquals(email.getText(), "Эл. почта");
        assertTest.assertEquals(password.getText(), "Придумайте пароль");
        assertTest.assertAll();
    }

    public void validationOfRegisterFields() {
        SoftAssert assertTest = new SoftAssert();
        SelenideElement lohInButton = $(By.xpath("//*[@class='header__button ng-star-inserted']")).should(Condition.enabled);
        lohInButton.click();
        SelenideElement registerLink = $(By.xpath("//*[@class='auth-modal__register-link button button--link ng-star-inserted']")).should(Condition.enabled);
        registerLink.click();
        SelenideElement enterButton = $(By.xpath("//*[@class='button button--large button--green auth-modal__submit']"))
                .should(Condition.enabled);
        enterButton.click();
        SelenideElement firstNameValidation = $(By.xpath("//*[@class='form__row js-name validation_type_error']//p[@class='validation-message ng-star-inserted']"))
                .should(Condition.visible);
        SelenideElement lastNameValidation = $(By.xpath("//*[@class='form__row js-surname validation_type_error']//p[@class='validation-message ng-star-inserted']"))
                .should(Condition.visible);
        SelenideElement phoneValidation = $(By.xpath("//*[@class='form__row js-phone validation_type_error']//p[@class='validation-message ng-star-inserted']"))
                .should(Condition.visible);
        SelenideElement emailValidation = $(By.xpath("//*[@class='form__row js-email validation_type_error']//p[@class='validation-message ng-star-inserted']"))
                .should(Condition.visible);
        SelenideElement passwordFieldValidation = $(By.id("registerUserPassword"))
                .shouldHave(Condition.empty, Condition.visible);
        assertTest.assertEquals(firstNameValidation.getText(), "Введите свое имя на кириллице");
        assertTest.assertEquals(firstNameValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertEquals(lastNameValidation.getText(), "Введите свою фамилию на кириллице");
        assertTest.assertEquals(lastNameValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertEquals(phoneValidation.getText(), "Введите свой номер телефона");
        assertTest.assertEquals(phoneValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertEquals(emailValidation.getText(), "Введите свою эл. почту");
        assertTest.assertEquals(emailValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertEquals(passwordFieldValidation.getCssValue("border-color"), "rgb(248, 65, 71)");
        assertTest.assertAll();
    }
}
