package com.nixsolutions.dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "data-provider-login")
    public static Object[][] dataProviderLogin() {
        return new Object[][]{{"login1", "Pass1"}, {"log2", "Pass2"}};
    }

    @DataProvider(name = "data-provider-new-user")
    public static Object[][] dataProviderNewUser() {
        return new Object[][]{{"testvk11@mail.ru", "VkFirst1","2"}, {"testvk21@mail.ru", "Vk Last1","2"}};
    }

    @DataProvider(name="data-provider-exist-user-name")
    public static Object[][] dataProviderExistUserName(){
        return new Object[][]{{"newemail@mail.ru", "VkFirst1","2"}};
    }

    @DataProvider(name="data-provider-exist-user-email")
    public static Object[][] dataProviderExistUserEmail(){
        return new Object[][]{{"testvk21@mail.ru", "newName","2"}};
    }

    @DataProvider(name = "data-provider-login-user")
    public static Object[][] dataProviderLoginUser() {
        return new Object[][]{{"testvk11@mail.ru", "2"}, {"testvk2@mail.ru", "2"}};
    }

    @DataProvider(name = "data-provider-incorrect-login-user")
    public static Object[][] dataProviderIncorrectLoginUser() {
        return new Object[][]{{"testvk21@mail.ru", "wrong"}, {"testvk2_wrong@mail.ru", "2"}};
    }

    @DataProvider(name = "data-provider-user-with-task")
    public static Object[][] dataProviderCreateUserWithTask() {
        return new Object[][]{{"testvk41@mail.ru", "First Last name1", "First task", "description",
                "Second task", "description","19","20","hobby","address","name1","surname1",
                "fathername1","cat","dog","parrot","cavy","hamster","squirrel","333 33 33","123456789012","m","04.10.1988","10.10.2020"}};
    }

    @DataProvider(name = "data-provider-creation-company")
    public static Object[][] dataProviderCreateCompany() {
        return new Object[][]{{"Company 1", "ООО","testvk2@mail.ru","testvk3@mail.ru","testvk1@mail.ru"},
                {"Company 2", "ООО","testvk2@mail.ru","testvk3@mail.ru","testvk1@mail.ru"}};
    }

    @DataProvider(name = "data-provider-creation-company-wrong-owner")
    public static Object[][] dataProviderCreateCompanyWrongOwner() {
        return new Object[][]{{"Company 3", "ООО","testvk2@mail.ru","testvk3@mail.ru","testvk1_wrong@mail.ru"},
                {"Company 4", "ООО","testvk2@mail.ru","testvk3@mail.ru","testvk1_wrong@mail.ru"}};
    }

    @DataProvider(name = "data-provider-creation-company-without-users")
    public static Object[][] dataProviderCreateCompanyWithoutUsers() {
        return new Object[][]{{"Company 3", "ООО","","testvk3_notexist@mail.ru","testvk1@mail.ru"},
                {"Company 4", "ООО","testvk2_notexist@mail.ru","","testvk1@mail.ru"}};
    }

    @DataProvider(name = "data-provider-creation-company-with-wrong-type")
    public static Object[][] dataProviderCreateCompanyWithWrongType() {
        return new Object[][]{{"Company 1", "ОAО", "testvk2@mail.ru", "testvk3@mail.ru", "testvk1@mail.ru"},
                {"Company 2", "TOB", "testvk2@mail.ru", "testvk3@mail.ru", "testvk1@mail.ru"}};
    }
}