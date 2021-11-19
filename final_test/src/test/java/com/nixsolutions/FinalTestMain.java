package com.nixsolutions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.nixsolutions.apiTests.createCompany.CreateNewCompany;
import com.nixsolutions.apiTests.createNewUsers.CreateNewUserWithTask;
import com.nixsolutions.apiTests.createNewUsers.CreateNewUsersWithoutTasks;
import com.nixsolutions.uiTests.authenticationPages.LogInPopUp;
import com.nixsolutions.uiTests.basketPopUp.BasketPopUp;
import com.nixsolutions.uiTests.configBaseTest.BaseTest;
import com.nixsolutions.dataProvider.DataProviderClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;

public class FinalTestMain extends BaseTest {
    LogInPopUp logInPopUp = new LogInPopUp();
    CreateNewUsersWithoutTasks createNewUsersWithoutTasks = new CreateNewUsersWithoutTasks();
    CreateNewUserWithTask createNewUserWithTask = new CreateNewUserWithTask();
    CreateNewCompany createNewCompany = new CreateNewCompany();
    BasketPopUp basketPopUp = new BasketPopUp();
    SoftAssert assertTest = new SoftAssert();

    //UI
    @Test(dataProvider = "data-provider-login", dataProviderClass = DataProviderClass.class, groups = "authentication")
    public void verificationLogInFieldsIsEditable(String login, String password) {
        logInPopUp.LogIn(login, password);
    }

    @Test (groups = "authentication")
    public void verificationTitleOfLogInFields() {
        logInPopUp.titleOfLoginFields();
    }

    @Test (groups = "authentication")
    public void validationLogInPopUpLoginField() {
        logInPopUp.validationLoginField();
    }

    @Test(groups = "authentication")
    public void validationLogInPopUpPasswordField() {
        logInPopUp.validationPasswordField();
    }

    @Test(groups = "authentication")
    public void verificationRegisterPopUpFieldsIsEditable() {
        logInPopUp.registerPopUp("FistName", "LastName", "44 455 54 54", "test@nix.com", "Password");
    }

    @Test(groups = "authentication")
    public void verificationTitleOfRegisterFields() {
        logInPopUp.titleOfRegisterFields();
    }

    @Test(groups = "authentication")
    public void validationRegisterFields() {
        logInPopUp.validationOfRegisterFields();
    }

    @Test(invocationCount = 2,groups = "validation")
    public void verificationOfGoodInBasket() {
        basketPopUp.selectSection();
        SelenideElement good1 = $(By.xpath("//span[@class = 'goods-tile__title' and text() =' Компьютер Cobra I11.8.H1S2.165.101 ']")).should(Condition.exist);
        String text = good1.getText().trim();
        good1.click();
        basketPopUp.addToBasket(text);
    }

    @Test(groups = "validation")
    public void removeFromBasket() {
        basketPopUp.selectSection();
        SelenideElement good1 = $(By.xpath("//span[@class = 'goods-tile__title' and text() =' Компьютер Cobra I11.8.H1S2.165.101 ']")).should(Condition.exist);
        String text = good1.getText().trim();
        good1.click();
        basketPopUp.addToBasket(text);
        basketPopUp.removeFromBasket();
    }

    @Test(groups = "validation")
    public void orderForm() {
        basketPopUp.search("Lg");
        SelenideElement select = $(By.xpath("//*[@class='goods-tile__title']")).should(Condition.visible);
        select.click();
        SelenideElement addGood = $(By.xpath("//button[@class = 'buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']"))
                .should(Condition.exist, Condition.visible);
        addGood.click();
        basketPopUp.orderForm();
    }

    //API
    //create new user

    @Test(dataProvider = "data-provider-new-user", dataProviderClass = DataProviderClass.class, groups = "api_user")
    public void verificationRegisterNewUser(String email, String name, String password) {
        createNewUsersWithoutTasks.registerNewUser(email, name, password);
    }

    @Test(dataProvider = "data-provider-exist-user-name", dataProviderClass = DataProviderClass.class, groups = "api_user")
    public void verificationUserNameExist(String email, String name, String password) {
        createNewUsersWithoutTasks.UserNameExist(email, name, password);
    }

    @Test(dataProvider = "data-provider-exist-user-email", dataProviderClass = DataProviderClass.class, groups = "api_user")
    public void verificationUserEmailExist(String email, String name, String password) {
        createNewUsersWithoutTasks.UserEmailExist(email, name, password);
    }

    @Test(dataProvider = "data-provider-login-user", dataProviderClass = DataProviderClass.class, groups = "api_user")
    public void verificationUserLogIn(String email, String password) {
        createNewUsersWithoutTasks.UserLogIn(email, password);
    }

    @Test(dataProvider = "data-provider-incorrect-login-user", dataProviderClass = DataProviderClass.class, groups = "api_user")
    public void verificationUserIncorrectLogIn(String email, String password) {
        createNewUsersWithoutTasks.UserIncorrectLogIn(email, password);
    }

    //create createuserwithtasks

    @Test(dataProvider = "data-provider-user-with-task", dataProviderClass = DataProviderClass.class, groups = "api_user_with_task")
    public void verificationCreateUserWithTask(String email, String name, String title1, String desc1, String title2, String desc2,
                                               String comp1, String comp2, String hobby, String address, String name1, String surname1,
                                               String fathername1, String cat, String dog, String parrot, String cavy,
                                               String hamster, String squirrel, String phone, String inn, String gender,
                                               String birthday, String dateStart) {
        createNewUserWithTask.createUserWithTask(email, name, title1, desc1, title2, desc2, comp1, comp2, hobby, address, name1,
                surname1, fathername1, cat, dog, parrot, cavy, hamster, squirrel, phone, inn, gender, birthday, dateStart);
    }

    //create Company

    @Test(dataProvider = "data-provider-creation-company", dataProviderClass = DataProviderClass.class, groups = "api_company")
    public void verificationCreateCompany(String companyName, String companyType, String companyUser1,
                                          String companyUser2, String emailOwner) {
        createNewCompany.createNewOrganization(companyName, companyType, companyUser1, companyUser2, emailOwner);
    }

    @Test(dataProvider = "data-provider-creation-company-wrong-owner", dataProviderClass = DataProviderClass.class, groups = "api_company")
    public void verificationCreateCompanyWithWrongOwner(String companyName, String companyType, String companyUser1,
                                                        String companyUser2, String emailOwner) {
        createNewCompany.createOrganizationWithWrongOwner(companyName, companyType, companyUser1, companyUser2, emailOwner);
    }

    @Test(dataProvider = "data-provider-creation-company-without-users", dataProviderClass = DataProviderClass.class, groups = "api_company")
    public void verificationCreateCompanyWithoutUsers(String companyName, String companyType, String companyUser1,
                                                      String companyUser2, String emailOwner) {
        createNewCompany.createOrganizationWithoutUsers(companyName, companyType, companyUser1, companyUser2, emailOwner);
    }

    @Test(dataProvider = "data-provider-creation-company-with-wrong-type", dataProviderClass = DataProviderClass.class, groups = "api_company")
    public void verificationCreateCompanyWithWrongType(String companyName, String companyType, String companyUser1,
                                                       String companyUser2, String emailOwner) {
        createNewCompany.createOrganizationWithWrongType(companyName, companyType, companyUser1, companyUser2, emailOwner);
    }
}